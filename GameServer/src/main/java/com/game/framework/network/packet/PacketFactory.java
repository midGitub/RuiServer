package com.game.framework.network.packet;

import com.game.framework.network.code.ProtoPacketMessage;
import com.game.framework.network.packet.exception.NoSuchPacketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 包工厂
 *
 * @author liguorui
 * @date 2016-01-06
 */
public class PacketFactory {

    private static final Logger logger = LoggerFactory.getLogger(PacketFactory.class);

    private static final PacketFactory factory = new PacketFactory();
    private Map<Short, Class<? extends AbstractPacket>> commandId2PacketClass;
    private Map<Class<? extends AbstractPacket>, Short> packetClass2CommandId = new HashMap<Class<? extends AbstractPacket>, Short>();

    private PacketFactory() {
    }

    public static PacketFactory getInstance() {
        return factory;
    }

    /**
     * 通过消息ID创建一个包
     *
     * @param packetId
     * @return
     */
    public AbstractPacket createPacket(short packetId) {
        Class<? extends AbstractPacket> clazz = commandId2PacketClass.get(packetId);
        if (clazz == null) {
            throw new NoSuchPacketException(packetId);
        }

        try {
            AbstractPacket newInstance = clazz.newInstance();
            newInstance.setCommandId(packetId);
            return newInstance;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractPacket> T createPacket(Class<T> clazz) {
        try {
            AbstractPacket newInstance = clazz.newInstance();
            Short commandId = packetClass2CommandId.get(clazz);
            if (commandId == null) {
                throw new NoSuchPacketException(clazz);
            }
            newInstance.setCommandId(commandId);
            return (T) newInstance;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 通过读取buffer中的消息ID和其他数据创建一个包。</br> 读取方法需要由每个包的read(ChannelBuffer buffer)决定。
     *
     * @param byteBuf
     * @return
     * @throws Exception
     */
//	public AbstractPacket createPacket(ByteBuf byteBuf) throws Exception {
//		short packetId = byteBuf.readShort();
//		AbstractPacket createPacket = createPacket(packetId);
//		if (createPacket != null) {
//			byte[] bytes = new byte[byteBuf.readableBytes()]; //protobuf bytes
//			byteBuf.readBytes(bytes);
//			createPacket.createPacket(packetId, bytes);
//		}
//		return createPacket;
//	}

    /**
     * @param packetMessage protobuf内容（协议号+内容）
     * @return 消息包
     * @throws Exception
     */
    public AbstractPacket createPacket(ProtoPacketMessage packetMessage) throws Exception{
        short packetId = packetMessage.getPacketId();
        AbstractPacket createPacket = createPacket(packetId);
        if (createPacket != null) {
            createPacket.setProtobufBytes(packetMessage.getContent());
        }
        return createPacket;
    }

    /**
     * 通过request创建一个包
     *
     * @param
     * @return
     * @throws Exception
     */
//	public AbstractPacket createPacket(Request request) throws Exception {
//		short packetId = request.getPacketId();
//		AbstractPacket createPacket = createPacket(packetId);
//		if (createPacket != null) {
//			createPacket.read(request);
//		}
//		return createPacket;
//	}
    public void init(Map<Short, Class<? extends AbstractPacket>> packets) {
        this.commandId2PacketClass = packets;

        for (Entry<Short, Class<? extends AbstractPacket>> entry : packets.entrySet()) {
            packetClass2CommandId.put(entry.getValue(), entry.getKey());
        }
    }

    public Map<Short, Class<? extends AbstractPacket>> getAllPacket() {
        return commandId2PacketClass;
    }
}
