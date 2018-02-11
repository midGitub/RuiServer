package com.game.framework.network.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 *  游戏解码器(收到req包解码)
 * @author liguorui
 * @date 2017/7/5 23:28
 */
public class GamePacketDecoder extends ByteToMessageDecoder {

    private final static Logger logger				= LoggerFactory.getLogger(GamePacketDecoder.class);

    private final static byte	STATE_HEADE			= 0;
    private final static byte	STATE_BODY		= 1;
    private final static byte	STATE_FRAME_ERROR	= 10;

    private byte state = STATE_HEADE;
    private byte[] headerBytes;

    /**
     *
     * @param headerBytes 包头自定义内容
     */
    public GamePacketDecoder(byte[] headerBytes) {
        if (headerBytes == null) {
            throw new IllegalArgumentException("Please offer a header.");
        }
        this.headerBytes = headerBytes;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state) {
            case STATE_HEADE:
                if (in.readableBytes() >= headerBytes.length) {
                    if (checkHeader(in)) {
                        state = STATE_BODY;
                    } else {
                        state = STATE_FRAME_ERROR;
                        onFrameDecodeError(ctx, in, out);
                        break;
                    }
                } else {
                    break;
                }
            case STATE_BODY:
                if (in.readableBytes() >= 2) {
                    in.markReaderIndex();
                    int bodySize = in.readInt();
                    logger.info("RECV BODY bodySize:" + bodySize+",in.readableBytes()="+in.readableBytes());
                    if (in.readableBytes() >= bodySize) {
                        out.add(in.readBytes(bodySize));
                        state = STATE_HEADE;
                    } else {
                        in.resetReaderIndex();
                    }
                }
                break;
            case STATE_FRAME_ERROR:
                onFrameDecodeError(ctx, in, out);
                break;
        }
    }

    /**
     * 当帧头部信息解码出错时
     *
     * @param ctx
     * @param in
     * @param out
     */
    private void onFrameDecodeError(	ChannelHandlerContext ctx,
                                          ByteBuf in,
                                          List<Object> out) {
        logger.error("GamePacketDecoder.onFrameDecodeError decode error! {},{}",in,out);
        in.clear();
        ctx.close();
    }

    /**
     * 检查头部信息
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
