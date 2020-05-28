import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.swing.*;

public class Base64Operate {
    private String str = "";
    private String str_base64 = "";
    private Base64.Encoder b64_encode = Base64.getEncoder();
    private Base64.Decoder b64_decode = Base64.getDecoder();

    public String StrEncode(){
        String result = this.b64_encode.encodeToString(this.str.getBytes());
        return result;
    }

    public String StrDecode(){
        String result = "";
        try {
            result = new String(this.b64_decode.decode(this.str_base64), "UTF-8");
        } catch (UnsupportedEncodingException e){
            result = "";
        } finally {
            return result;
        }
    }

    public String getStr() {
        return str;
    }

    public String getStr_base64() {
        return str_base64;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setStr_base64(String str_base64) {
        this.str_base64 = str_base64;
    }

}
