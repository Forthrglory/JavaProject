package Client_Service;

import Interface.Interface;

import Client_Service.Chat_View.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//客户端启动监听服务
public class MyClient_Service extends Thread {
    private int ClientPort = 23333;

    public void setServicePort(int Port){
        this.ClientPort = Port;
    }

    public int getServicePort(){
        return this.ClientPort;
    }

    public void run(){
        try {
            ServerSocket s = new ServerSocket(this.ClientPort);
            while(true){
                Socket sc = s.accept();
                Interface data = new Interface();
                data.setIP(sc.getInetAddress().toString().split("/")[1]);
                data.setSc(sc);
                ChatWindow1to1 cw1 = new ChatWindow1to1(data);
                data.setCW1(cw1);
                new MyClientThread_Read(data).start();
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}