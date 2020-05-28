package Service;
import Interface.*;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

//服务器启动公共聊天室
public class MyService_ChatRoom extends Thread {
    private int ClientPort = 24444;

    public void setServicePort(int Port){
        this.ClientPort = Port;
    }

    public int getServicePort(){
        return this.ClientPort;
    }


    public void run(){
        try {
            ServerSocket s = new ServerSocket(this.ClientPort);
            Vector<Socket> sc_arr = new Vector<Socket>();
            Friend.List.put("管理员", "192.168.43.97");
            while(true){
                Socket sc = s.accept();
                sc_arr.add(sc);
                System.out.println(sc.getInetAddress() + ":" + sc.getPort() + "已加入");

                //服务器打印进程
                new MyServiceThread_Read(sc, sc_arr).start();
                Interface Int = new Interface();
                Int.setUsername("管理员");

                //服务器打印
                new MyServiceThread_Me(sc_arr, Int).start();
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}