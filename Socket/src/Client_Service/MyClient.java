package Client_Service;

import Client_Service.Chat_View.ChatWindow1to1;
import Interface.Interface;

import Client_Service.Chat_View.ChatWindow;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

//启动私聊&连接公共聊天室
public class MyClient{
    private Interface Interface;
    private int ServicePort = 23333;
    private boolean type;

    public MyClient(Interface Int, boolean t){
        this.Interface = Int;
        this.type = t ;
    }

    public void SocketClient(){
        Socket s = new Socket();
        try{
            s.connect(new InetSocketAddress(this.Interface.getIP(), this.ServicePort));
            this.Interface.setSc(s);

            Interface Service_Int = new Interface();//将服务端信息写入
            Service_Int.setSc(s);
            Service_Int.setIP(s.getInetAddress().getHostAddress());


            if (type == false){//客户端与客户端建立连接

                ChatWindow1to1 cw1 = new ChatWindow1to1(Service_Int);
                Service_Int.setCW1(cw1);
            } else {//客户端与服务端建立连接
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                BufferedReader BR = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BW.write("Username:" + this.Interface.getUsername() + "&&" + s.getLocalAddress().toString().split("/")[1] + "\n");//将客户端username写入
                BW.flush();
                Service_Int.setUsername(this.Interface.getUsername());
                ChatWindow cw = new ChatWindow(Service_Int);
                Service_Int.setCW(cw);
            }


            new MyClientThread_Read(Service_Int).start();//启动读服务端信息进程
        } catch (IOException e){
            System.out.println(e);
        }
    }

}