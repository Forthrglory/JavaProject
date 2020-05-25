package Client_Service;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import Client_Service.Chat_View.ChatWindow;
import Interface.*;

import javax.swing.*;

//启动客户端打印消息进程
class MyClientThread_Read extends Thread{
    private Interface Interface;

    public MyClientThread_Read(Interface Int){
        this.Interface = Int;
    }

    public void run(){
        while(true){
            try {
                BufferedReader BR = new BufferedReader(new InputStreamReader(this.Interface.getSc().getInputStream()));
                String word = BR.readLine();

                //添加在线列表
                if ((word.length() > 11) && (word.substring(0, 11).equals("FriendList:"))){
                    String FL = word.substring(11);
                    String[] list = FL.split("\\*\\*");
                    Friend.List.clear();
                    for (String i : list){
                        String[] j = i.split(":");
                        Friend.List.put(j[0], j[1]);
                    }

                    new MyClient_Refresh(this.Interface.getCW()).start();
                } else if (this.Interface.getCW() != null && this.Interface.getCW1() == null){
                    this.Interface.getCW().EchoMessage(word + "\n");
                } else if (this.Interface.getCW1() != null){
                    this.Interface.getCW1().EchoMessage(word + "\n");
                }

            } catch (IOException e){
                System.out.println(this.Interface.getUsername() + "已断开");
                try {
                    this.Interface.getSc().close();
                } catch (IOException i){
                    JOptionPane.showMessageDialog(null,"连接失败！");
                }
                break;
            }
        }
    }
}

class MyClient_Refresh extends Thread{
    private ChatWindow cw;

    public MyClient_Refresh(ChatWindow cw){
        this.cw = cw;
    }

    public void run(){
        cw.Refresh();
    }
}