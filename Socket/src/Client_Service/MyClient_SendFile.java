package Client_Service;

import File.SocketMoveFile;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyClient_SendFile {
    private String IP;
    private int port = 23332;
    private String path;

    public MyClient_SendFile(String ip, String path){
        this.IP = ip;
        this.path = path;
    }

    public void Connet(){
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(this.IP, this.port));

            new SocketMoveFile(s, path).start();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"文件传输管道连接失败！");
        }
    }
}
