package Service;

import Interface.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static java.lang.Thread.*;


//启动服务器打印公共聊天室消息进程
class MyServiceThread_Read extends Thread{
    private Socket sc;
    private Vector<Socket> sc_arr;

    public MyServiceThread_Read(Socket s, Vector<Socket> s_arr){
        this.sc = s;
        this.sc_arr = s_arr;
    }

    public void run(){
        String word;
        while(true){
            try {
                BufferedReader BR = new BufferedReader(new InputStreamReader(this.sc.getInputStream()));
                word = BR.readLine();

                if ((word.length() > 9) && (word.substring(0,9).equals("Username:"))){
                    String NameIP = null;
                    NameIP = word.substring(9);

                    String[] data = NameIP.split("&&");

                    Friend.List.put(data[0], data[1]);

                    new MySerivice_FriendList(this.sc_arr).start();

                } else {
                    System.out.println(word);
                    new MyServiceThread_Broadcast(this.sc_arr, this.sc, word).start();
                }

            } catch (IOException e){
                System.out.println(this.sc.getInetAddress() + ":" + this.sc.getPort() + "已退出");

                //移除在线列表
                String username = new String();
                String IP = new String();
                IP = this.sc.getInetAddress().toString().split("/")[1];
                for (String key : Friend.List.keySet()){
                    if (Friend.List.get(key).equals(IP)){
                        username = key;
                        break;
                    }
                }

                Friend.List.remove(username);


                try {
                    this.sc.close();
                    this.sc_arr.remove(this.sc);
                } catch (IOException i){
                    System.out.println("Service Close Error!");
                }
                new MySerivice_FriendList(sc_arr).start();
                break;
            }
        }
    }
}

/*class MyServiceThread_Others extends Thread {
    private Socket sc;

    public MyServiceThread_Others(Socket s){
        this.sc = s;
    }

    public void run(){
        MyServiceThread_Read mR = new MyServiceThread_Read(this.sc);
        mR.start();
    }
}

class MyClientThread_Others extends Thread {
    private Socket sc;
    private String IP;

    public MyClientThread_Others(Socket s, String ip){
        this.sc = s;
        this.IP = ip;
    }

    public void run(){
        MyClientThread_Read mR = new MyClientThread_Read(this.sc, this.IP);
        mR.start();
    }
}*/



//启动服务器发送管理员消息进程
class MyServiceThread_Me extends Thread {
    private Vector<Socket> sc_arr;
    private Interface Interface;

    public MyServiceThread_Me(Vector<Socket> s_arr, Interface Int){
        this.sc_arr = s_arr;
        this.Interface =  Int;
    }

    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String word;
            while (true) {
                word = "管理员：" + keyboard.readLine();
                System.out.println(word);
                for (Socket i : this.sc_arr){
                    BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(i.getOutputStream()));
                    BW.write(word + '\n');
                    BW.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("MyServiceThread_Me + " + e);
        }
    }
}


//启动公共聊天室消息转发进程
class MyServiceThread_Broadcast extends Thread{
    private Vector<Socket> sc_arr;
    private Socket sc;
    private String Message;

    public MyServiceThread_Broadcast(Vector<Socket> s_arr, Socket s, String Message){
        this.sc_arr = s_arr;
        this.sc = s;
        this.Message = Message;
    }

    public void run() {
        try {
            for (Socket i : this.sc_arr){
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(i.getOutputStream()));
                BW.write(this.Message + '\n');
                BW.flush();
            }
        } catch (IOException e) {
            System.out.println("MySeriviceThread_Broadcast  + " + e);
        }
    }
}


//发送好友列表
class MySerivice_FriendList extends Thread{
    private Vector<Socket> sc_arr;

    public MySerivice_FriendList(Vector<Socket> s_arr){
        this.sc_arr = s_arr;
    }

    public void run(){
        try {
            for (Socket i : this.sc_arr){
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(i.getOutputStream()));

                String FriendList = "";
                for(Map.Entry<String, String> entry : Friend.List.entrySet()){
                    FriendList += entry.getKey() + ":" + entry.getValue() + "**";
                }
                BW.write("FriendList:" + FriendList + '\n');
                BW.flush();
            }
        } catch (IOException e) {
            System.out.println("MyService_FriendList  + " + e);
        }
    }

}