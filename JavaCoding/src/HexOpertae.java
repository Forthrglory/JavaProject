import java.io.UnsupportedEncodingException;

public class HexOpertae {
    private String str = "";
    private String hex = "";

    public String strTohex(){
        String result = "";
        try {
            char[] chars = "0123456789ABCDEF".toCharArray();
            StringBuilder sb = new StringBuilder("");
            byte[] bs = this.str.getBytes("UTF-8");
            int bit;
            for(int i = 0; i < bs.length; i++){
                bit = (bs[i] & 0x0f0) >> 4;
                sb.append(chars[bit]);
                bit = bs[i] & 0x0f;
                sb.append(chars[bit]);
            }
            result = sb.toString().trim();
        } catch (UnsupportedEncodingException e){
            result = "";
        } finally {
            return result;
        }

    }

    public String hexTostr(){
        String result;
        String map = "0123456789ABCDEF";
        char[] hexs = this.hex.toCharArray();
        byte[] bytes = new byte[this.hex.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++){
            n = map.indexOf(hexs[2 * i]) * 16;
            n += map.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        result = new String(bytes);
        return result;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getStr() {
        return str;
    }

    public String getHex() {
        return hex;
    }
}
