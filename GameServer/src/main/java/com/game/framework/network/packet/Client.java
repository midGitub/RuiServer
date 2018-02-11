package com.game.framework.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 客户端模拟
 * @author liguorui
 * @date 2017/7/1 20:44
 */
public class Client {

    private static final byte[] PACKET_HEAD = { '2', '0', '1', '7' };

    private static final String IP = "localhost";

    private static final int PORT = 8087;

    private static final List<Socket> socketList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            test();
//            int taskNum = 1000;
//            CountDownLatch latch = new CountDownLatch(taskNum);
//            ExecutorService service = Executors.newFixedThreadPool(50);
//            for (int i = 0; i < taskNum; i++) {
//                service.execute(new ScenePlayerNumTask(latch));
//            }
//            latch.await();
//            System.out.println("task finish ");
//            service.shutdownNow();
//            for (Socket socket : socketList) {
//                socket.close();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ScenePlayerNumTask implements  Runnable {
        private CountDownLatch latch;

        public ScenePlayerNumTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            Socket socket = new Socket();
            try {
                int readTimeOut = 2000;
                int connectTimeOut = 3000;
                socket.setSoTimeout(readTimeOut);
                socket.bind(null);
                socket.connect(new InetSocketAddress(IP,PORT), connectTimeOut);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                reqScenePlayerNum(out,in);
                socketList.add(socket);
//                Thread.sleep(10);
//                socket.close();
            } catch (Exception e) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.println("端口："+ socket.getLocalPort() + "\r\n");
                e.printStackTrace(printStream);
                printStream.close();
                System.err.println(outputStream.toString());
                try {
                    socket.close();
                } catch (Exception e1) {
                }
            } finally {
                latch.countDown();
            }
        }
    }

    public static void test() throws Exception {
        int readTimeOut = 5000;
        int connectTimeOut = 5000;
        long startTime = System.currentTimeMillis();
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(IP,PORT), connectTimeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("cost:" + (System.currentTimeMillis() - startTime) + "毫秒");
        System.err.println(socket.getLocalAddress() + ",port:" + socket.getLocalPort());
        socket.setSoTimeout(readTimeOut);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        startTime = System.currentTimeMillis();

        try {
//            sendMsg(out, in);
            login(out, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("cost:" + (System.currentTimeMillis() - startTime) + "毫秒");
        socket.close();
    }

    public static void sendMsg(DataOutputStream out, DataInputStream in) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(1);
        buffer.putShort((short)10);
        buffer.putLong(201010); //玩家id
        writeString(buffer, "玩家名字");

        short packetId = 1002; //包id

        send(out, packetId, buffer);

        in.skip(10);
        int back = in.readInt();
        String back2 = readString(in);
        System.out.println("send player data");
    }

    public static void login(DataOutputStream out, DataInputStream in) throws Exception {
        System.out.println("loginaaaa======");
        ByteBuffer buffer = ByteBuffer.allocate(64);
        writeString(buffer, "aaaaabbbb");
        writeString(buffer, "123456");
        buffer.putInt(101);
        buffer.put((byte)2);
        buffer.putShort((short)30);
        short packetId = 10001; //包id

        send(out, packetId, buffer);

//        in.skip(100);
        int back = in.readInt();
        System.out.println("login data back="+back);
    }

    public static void reqScenePlayerNum(DataOutputStream out, DataInputStream in) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(64);

        short packetId = 1001;

        send(out, packetId, buffer);

        in.skip(10);

        //读取返回的数据
        int back1 = in.readInt();
        String back2 = readString(in);
        short back3 = in.readShort();
    }

    public static void send(DataOutputStream out, short packetId, ByteBuffer buffer) throws Exception{
        out.write(PACKET_HEAD);
        out.writeInt(buffer.position() + 2);
        out.writeShort(packetId);

        buffer.flip();
        buffer.compact();

        byte[] byteArray = buffer.array();
        System.err.println("byteArray="+byteArray+",byteArray.size="+byteArray.length+",buffer.position()="+buffer.position());
        out.write(byteArray, 0, buffer.position());
        out.flush();
    }

    /**
     * 将读到字节数组专程字符串
     * @param in
     * @return
     */
    public static String readString(DataInputStream in) {
        try {
            short length = in.readShort();
            byte[] content = new byte[length];
            in.read(content);
            return new String(content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串内容写给客户端
     * @param buffer
     * @param str
     */
    public static void writeString(ByteBuffer buffer, String str) {
        try {
            if (str != null) {
                byte[] content = str.getBytes("utf-8");
                buffer.putShort((short)content.length);
                buffer.put(content);
            } else {
                buffer.putShort((short)0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
