package Interface;

import Client_Service.Chat_View.*;
import java.net.Socket;
import java.util.HashMap;

public class Interface {
    private String Username;
    private String Message;
    private String Path;
    private String IP;
    private String GoalPath;
    private Socket sc;
    private ChatWindow CW;
    private ChatWindow1to1 CW1;
    private HashMap<String, String> Username_IP;

    public void setUsername(String Username){
        this.Username = Username;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public void setPath(String path) {
        this.Path = path;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setGoalPath(String goalPath) {
        this.GoalPath = goalPath;
    }

    public void setSc(Socket s){
        this.sc = s;
    }

    public void setCW(ChatWindow cw){
        this.CW = cw;
    }

    public void setCW1(ChatWindow1to1 CW1) {
        this.CW1 = CW1;
    }

    public void setUsername_IP(String Username, String IP) {
        this.Username_IP.put(Username, IP);
    }














    public String getUsername(){
        return this.Username;
    }

    public String getPath() {
        return this.Path;
    }

    public String getMessage() {
        return this.Message;
    }

    public String getIP(){
        return this.IP;
    }

    public String getGoalPath() {
        return this.GoalPath;
    }

    public Socket getSc() {
        return this.sc;
    }

    public ChatWindow getCW() {
        return this.CW;
    }

    public ChatWindow1to1 getCW1() {
        return this.CW1;
    }

    public HashMap<String, String> getUsername_IP() {
        return this.Username_IP;
    }
}
