package FriendList;

import Client_Service.Chat_View.ChatWindow;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public final class FriendList {

    private static final String PATH = System.getProperty("user.dir") + "\\friend.txt";

    public static ArrayList<String> ReadFriendList() {
        File file = new File(PATH);
        ArrayList<String> arr = new ArrayList<String>();


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
            return arr;
        }

        try {
            BufferedReader BR = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String friend;
            try {
                while ((friend = BR.readLine()) != null) {
                    arr.add(new String(Base64.getDecoder().decode(friend), "UTF-8"));
                }

            } catch (IOException e) {

            }
        } catch (FileNotFoundException e) {

        }

        return arr;
    }

    public static void WriteFriendList(String IP, ChatWindow cw) {
        File file = new File(PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }

        try {
            String b64IP = new String(Base64.getEncoder().encode(IP.getBytes("UTF-8")), "UTF-8");
            try {
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                try {
                    BW.write(b64IP + "\n");
                    BW.flush();
                } catch (IOException e) {

                }
            } catch (FileNotFoundException e) {

            }
        } catch (UnsupportedEncodingException e) {

        }
        cw.Refresh_myfriend();
    }

    public static void WriteFriendList(ArrayList<String> arr, ChatWindow cw) {
        File file = new File(PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }

        try {
            for (String IP : arr){
                String b64IP = new String(Base64.getEncoder().encode(IP.getBytes("UTF-8")), "UTF-8");
                try {
                    BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                    try {
                        BW.write(b64IP + "\n");
                        BW.flush();
                    } catch (IOException e) {

                    }
                } catch (FileNotFoundException e) {

                }
            }
        } catch (UnsupportedEncodingException e) {

        }

        cw.Refresh_myfriend();
    }

    public static void DeleteFriendList(String IP, ChatWindow cw) {
        File file = new File(PATH);

        ArrayList<String> arr = FriendList.ReadFriendList();
        for (String i : arr){
            if (i.equals(IP)){
                arr.remove(i);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.flush();
            fw.close();
        } catch (IOException e){

        }

        FriendList.WriteFriendList(arr, cw);

    }
}
