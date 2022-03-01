package api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.LoginRequest;
import results.LoginResult;
import service.LoginService;
import util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
                throw new BadMethodException();
            }

            // creating request object
            InputStream reqBody = exchange.getRequestBody();
            String reqData = IO.readString(reqBody);
            System.out.println(reqData);

            // transforming json to model
            Gson gson = new Gson();
            LoginRequest request = gson.fromJson(reqData, LoginRequest.class);

            // performing action
            LoginService service = new LoginService();
            LoginResult result = service.login(request);

            // sending response
            if (result.getSuccess()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            OutputStream resBody = exchange.getResponseBody();
            String jsonResult = gson.toJson(result);
            IO.writeString(jsonResult, resBody);
            resBody.close();

        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        } catch (BadRequestException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        } catch (BadMethodException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
}