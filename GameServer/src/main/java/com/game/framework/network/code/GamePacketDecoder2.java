package com.game.framework.network.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 游戏帧解码器（Request形式）
 * @author liguorui
 * @date 2017/6/26 14:54
 */
public class GamePacketDecoder2 extends ByteToMessageDecoder {

    private final static Logger logger				= LoggerFactory.getLogger(GamePacketDecoder2.class);

    private final static byte	STATE_HEADE			= 0;
    private final static byte	STATE_BODY		= 1;
    private final static byte	STATE_FRAME_ERROR	= 10;
    private final static int MAX_BODY_SIZE = 1024*1024;
    private final static int HEADE_BODY_SIZE = 4;

    private byte				state				= STATE_HEADE;
    private int bodySize = 0;
    private byte[]				headerBytes;

    public GamePacketDecoder2(byte[] headerBytes) {
        if (headerBytes == null) {
            throw new IllegalArgumentException("Please offer a header.");
        }
        this.headerBytes = headerBytes;
    }

    @Override
    protected void decode(	ChannelHandlerContext ctx,
                              ByteBuf in,
                              List<Object> out) throws Exception {
        switch (state) {
            case STATE_HEADE:
                if (in.readableBytes() < headerBytes.length + HEADE_BODY_SIZE) {
                    return;
                }
                if (checkHeader(in)) {
                    bodySize = in.readInt();
                    state = STATE_BODY;
                } else {
                    System.err.println("checkHeader(in)");
                    state = STATE_FRAME_ERROR;
                    onFrameDecodeError(ctx, in, out);
                    break;
                }
            case STATE_BODY:
                if (bodySize > MAX_BODY_SIZE) {
                    throw new RuntimeException(String.format("bodySize[%s] too big!", bodySize));
                }
                logger.info("RECV BODY bodySize:" + bodySize+",in.readableBytes()="+in.readableBytes());
                if (in.readableBytes() <bodySize) {
                    return;
                }
                ByteBuf bodyBuf = ctx.alloc().buffer(bodySize);
                bodyBuf.writeBytes(in, bodySize);
                state = STATE_HEADE;
//                out.add(new ByteBufRequest(bodyBuf));
                out.add(bodyBuf);
                break;
            case STATE_FRAME_ERROR:
                onFrameDecodeError(ctx, in, out);
                break;

            default :
                throw new Error("GamePacketDecoder error!");
        }
    }

    /**
     * 当帧头部信息解码出错时
     *
     * @param ctx
     * @param in
     * @param out
     */
    protected void onFrameDecodeError(	ChannelHandlerContext ctx,
                                                   ByteBuf in,
                                                   List<Object> out) {
        logger.error("GamePacketDecoder2.onFrameDecodeError decode error! {},{}",in,out);
        in.clear();
        ctx.close();
    }

    /**
     * 检查头部信息
     *
     * @param in
     */
    private boolean checkHeader(ByteBuf in) {
        byte b = 0;
        for (int i = 0; i < headerBytes.length; i++) {
            if ((b = in.readByte()) != headerBytes[i]) {
                logger.error("Header of frame is unvalid:expect is `" + Arrays.toString(headerBytes) + "`, but real is `" + b + "` at " + i + " position");
                return false;
            }
        }
        return true;
    }
}
