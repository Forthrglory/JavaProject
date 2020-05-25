package File;

import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;

//接收文件
public class SaveFile extends Thread{
    private Socket sc;
    private String goalPath;

    public SaveFile(Socket s, String gP){
        this.sc = s;
        this.goalPath = gP;
    }

    public void run(){
        try {

            InputStream in = this.sc.getInputStream();
            BufferedReader BR = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            String data = BR.readLine();
            if (data.substring(0,9).equals("FileData:")){
                int size = Integer.valueOf(data.substring(9).split(":")[0]);
                String filename = data.substring(9).split(":")[1];
                this.goalPath = this.goalPath + "\\" + filename;

                File f = new File(this.goalPath);
                if(f.exists()){
                    JOptionPane.showMessageDialog(null,"文件已存在！");
                    return;
                }
                BufferedOutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(this.goalPath));

                int bytes = 0;
                for (int i = 0; i < size; i++){
                    bytes = in.read();
                    fileOutput.write(bytes);

                }
                fileOutput.flush();
            }

            JOptionPane.showMessageDialog(null,"文件传输完成！");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"文件传输失败!");
        }
    }
}