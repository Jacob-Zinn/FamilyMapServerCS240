package api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.RegisterRequest;
import results.RegisterResult;
import service.RegisterService;
import util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success=false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                // creating request object
                InputStream reqBody=exchange.getRequestBody();
                String reqData=readString(reqBody);
                System.out.println(reqData);

                // transforming json to model
                Gson gson=new Gson();
                RegisterRequest request = gson.fromJson(reqData, RegisterRequest.class);

                // performing action
                RegisterService service=new RegisterService();
                RegisterResult result=service.register(request);

                // sending response
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream resBody=exchange.getResponseBody();
                String jsonResult = gson.toJson(result);
                IO.writeString(jsonResult, resBody);
                resBody.close();

                success=true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                exchange.getResponseBody().close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);

            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }

    /*
            The readString method shows how to read a String from an InputStream.
    */
    private String readString(InputStream is) throws IOException {
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