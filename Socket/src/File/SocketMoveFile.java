package File;

import Interface.Interface;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;

//发送文件
public class SocketMoveFile extends Thread {
    private Socket sc;
    private String path;

    public void setPath(String p){
        this.path = p;
    }

    public String getPath() {
        return path;
    }

    public SocketMoveFile(Socket s, String p){
        this.sc = s;
        this.path = p;
    }

    public void run(){
        try {
            BufferedInputStream BI = new BufferedInputStream(new FileInputStream(path));
            BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
            OutputStream out = sc.getOutputStream();
            int size = BI.available();

            String[] filename = path.split("\\\\");
            String data = "FileData:" + size + ":" + filename[filename.length - 1];
            BW.write(data + "\n");
            BW.flush();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e){

            }

            int bytes = 0;
            for (int i = 0; i < size; i++){
                bytes = BI.read();
                out.write(bytes);
            }
            out.flush();
            JOptionPane.showMessageDialog(null,"传输完成！");

            sc.close();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"文件传输失败，对方拒绝连接");

        }
    }
}
