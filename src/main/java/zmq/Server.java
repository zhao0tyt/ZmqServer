package zmq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Date;

public class Server {
    public static void main(String args[]){
        //1.REP
//        try (ZContext context = new ZContext()) {
//            // Socket to talk to clients
//            org.zeromq.ZMQ.Socket socket = context.createSocket(org.zeromq.ZMQ.REP);
//            boolean success = socket.bind("tcp://127.0.0.1:6000");
//            System.out.println("bind success ="+success);
//            while (!Thread.currentThread().isInterrupted()) {
//                System.out.println("111");
//                // Block until a message is received
//                byte[] reply = socket.recv(0);
//                System.out.println("222");
//                // Print the message
//                System.out.println(
//                        "Received: [" + new String(reply, org.zeromq.ZMQ.CHARSET) + "]"
//                );
//
//                // Send a response
//                String response = "Hello, world!";
//                socket.send(response.getBytes(ZMQ.CHARSET), 0);
//            }
//        }

          //2.PUB
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.PUB);	//ZMQ.PUB 指定通讯模式为 【发布订阅模式】的 发布者
        socket.bind ("tcp://172.18.180.21:6000");	//绑定服务地址及端口

        while (true) {
            String time = new Date().getTime() + "";

            byte[] reply = time.getBytes();

            socket.send(reply, 0);
            System.out.println("@@@@ Server Send:" + time);
            try {
                Thread.sleep (3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        //3.PUSH
//        ZMQ.Context context = ZMQ.context(1);
//        ZMQ.Socket push  = context.socket(ZMQ.PUSH);
//        push.bind("tcp://127.0.0.1:6000");
//
//        for (int i = 0; i < 10; i++) {
//            push.send("hello".getBytes());
//        }
//        push.close();
//        context.term();


    }
}
