package util;

import java.io.*;

public class IO {
    public static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw=new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }


    // The readString method shows how to read a String from an InputStream.
    public static String readString(InputStream is) throws IOException {
        StringBuilder sb=new StringBuilder();
        InputStreamReader sr=new InputStreamReader(is);
        char[] buf=new char[1024];
        int len;
        while ((len=sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
