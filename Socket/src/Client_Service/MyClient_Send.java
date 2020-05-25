package Client_Service;

import Interface.*;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

//启动客户端发送消息
public class MyClient_Send {
    private Interface data;

    public MyClient_Send(Interface Int){
        this.data = Int;
    }

    public void Send() {
        try {
            BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(this.data.getSc().getOutputStream()));
            String word;
            word = this.data.getMessage();
            BW.write(word + '\n');
            BW.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"发送消息失败！");
        }
    }
}