package com.game.framework.network.netty.websocket;

import com.sun.javafx.binding.StringFormatter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * websocket处理
 * @author liguorui
 * @date 2017/8/6 00:11
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    public final static String WEBSOCKET= "websocket";
    public final static String UPGRADE= "Upgrade";

    public final static String WEBSOCKET_ADDRESS_FORMAT="ws://%s/";

    private WebSocketServerHandshaker handshaker;

    private static final Logger socketLog = LoggerFactory.getLogger("SocketLogger");

    /**
     * webSocket通信需要建立WebSocket连接,客户端首先要向服务端发起一个 Http 请求，
     * 这个请求和通常的 Http 请求不同，包含了一些附加头信息，其中附加信息"Upgrade:WebSocket"
     * 表明这是一个基于 Http 的 WebSocket 握手请求
     *   GET /chat HTTP/1.1
         Host: server.example.com
         Upgrade: websocket
         Connection: Upgrade
         Sec-WebSocket-Key: sdewgzgfewfsgergzgewrfaf==
         Sec-WebSocket-Protocol: chat, superchat
         Sec-WebSocket-Version: 13
         Origin: http://example.com
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) { //处理http请求
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) { //处理websocket请求
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 处理Http请求，完成WebSocket握手<br/>
     * 注意：WebSocket连接第一次请求使用的是Http
     * 成功返回：
     *   HTTP/1.1 101 Switching Protocols
     Upgrade: websocket
     Connection: Upgrade
     Sec-WebSocket-Accept: sdgdfshgretghsdfgergtbd=
     Sec-WebSocket-Protocol: chat
     * @param ctx
     * @param req
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // 该请求是不是websocket upgrade请求,HTTP解码失败，返回HHTP异常
        if (!req.decoderResult().isSuccess() || (!this.WEBSOCKET.equals(req.headers().get(this.UPGRADE).toString().toLowerCase()))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        String subProtocols = req.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);

        String host =req.headers().get(HttpHeaderNames.HOST).toString();

        String webAddress = StringFormatter.format(WEBSOCKET_ADDRESS_FORMAT, host).getValueSafe();
        // 正常WebSocket的Http连接请求，构造握手响应返回
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(webAddress, subProtocols, false);
        handshaker = factory.newHandshaker(req);
        if (handshaker == null) {// 请求头不合法, 导致handshaker没创建成功
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else { // 向客户端发送websocket握手,完成握手
            handshaker.handshake(ctx.channel(), req);
            socketLog.info("WebSocketServerHandler.handleHttpRequest.handshaker success ctx.channel()="+ctx.channel());
            System.out.println("#########WebSocketServerHandler.handleHttpRequest.handshaker success ctx.channel()="+ctx.channel()+",handleHttpRequest.webAddress="+webAddress);
        }
    }

    /*
    * 处理websocket 请求
    * */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof BinaryWebSocketFrame) {
            ctx.fireChannelRead(frame.retain());
            return;
        }

        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            socketLog.info("recieve PingWebSocketFrame from channel {}", ctx.channel());
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if (frame instanceof PongWebSocketFrame) {
            socketLog.info("recieve PongWebSocketFrame from channel {}", ctx.channel());
            return;
        }

        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            onWebSocketFrameColsed(ctx, frame);
            return;
        }

        socketLog.warn("handleWebSocketFrame.unhandle binary frame from channel {}", ctx.channel());
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /*
    * 处理websocket 关闭请求
    * */
    public void onWebSocketFrameColsed(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (handshaker != null){
            socketLog.info("recieve CloseWebSocketFrame from channel {}", ctx.channel());
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
    }

}
