package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Event;
import models.Person;
import models.User;
import requests.LoadRequest;
import results.LoadResult;
import service.LoadService;
import util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {

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

            LoadRequest request;
            Gson gson = new Gson();

            try {
                // transforming json to model
                JsonObject jsonObject = gson.fromJson(reqData, JsonObject.class);
                User[] userArray = gson.fromJson(jsonObject.get("users"), User[].class);
                Person[] personArray = gson.fromJson(jsonObject.get("persons"), Person[].class);
                Event[] eventArray = gson.fromJson(jsonObject.get("events"), Event[].class);

                request = new LoadRequest(userArray, personArray, eventArray);
            } catch (JsonSyntaxException e) {
                throw new BadRequestException("Error: request params contained poor syntax or were null");
            }

            // performing action
            LoadService service = new LoadService();
            LoadResult result = service.load(request);

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