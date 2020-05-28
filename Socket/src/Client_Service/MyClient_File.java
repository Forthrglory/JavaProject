package Client_Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Client_Service.Chat_View.ChatWindow;
import File.*;

import javax.swing.*;

//客户端启动监听文件传输服务
public class MyClient_File extends Thread {
    private int ClientPort = 23332;

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

                int flag = JOptionPane.showConfirmDialog(null, "文件传输",sc.getInetAddress().getHostAddress() + "申请传输文件", JOptionPane.YES_NO_OPTION);

                if (flag == 0){
                    String path = String.valueOf(JOptionPane.showInputDialog(null,"请输入储存路径：\n","文件传输",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"));
                    new SaveFile(sc, path).start();
                } else {
                    sc.close();
                }
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}