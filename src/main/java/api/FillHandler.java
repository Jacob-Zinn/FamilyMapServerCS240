package api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requests.FillRequest;
import results.FillResult;
import service.FillService;
import util.IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
                throw new BadMethodException();
            }
            // creating request object from url
            String urlPath = exchange.getRequestURI().toString();
            String[] params = urlPath.split("[/]"); // return ["","person","personID"]
            FillRequest fillRequest;
            if (params.length == 3) {
                fillRequest = new FillRequest(params[2]);
            } else if (params.length == 4) {
                int numGenerations;
                try {
                    numGenerations = Integer.parseInt(params[3]);
                } catch (NumberFormatException e) {
                    throw new BadRequestException("Must provide a integer value for number of generations");
                }
                fillRequest = new FillRequest(params[2], numGenerations);
            } else {
                throw new BadRequestException("incorrect num params in fill handler");
            }

            // performing action
            FillService service = new FillService();
            FillResult result = service.fill(fillRequest);

            // sending response
            if (result.getSuccess()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            OutputStream resBody = exchange.getResponseBody();
            Gson gson = new Gson();
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