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

        boolean success = false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                // creating request object from url
                String urlPath = exchange.getRequestURI().toString();
                String[] params = urlPath.split("[/]"); // return [IBM, Intel, HP, Cisco]
                FillRequest fillRequest;
                if (params.length == 2) {
                    fillRequest = new FillRequest(params[1]);
                } else if (params.length == 3){
                    int numGenerations = 4;
                    try {
                         numGenerations = Integer.parseInt(params[2]);
                    } catch (NumberFormatException e) {
                        throw new BadRequestException("Must provide a integer value for number of generations");
                    }
                    fillRequest = new FillRequest(params[1], numGenerations);
                } else {
                    throw new BadRequestException("incorrect num params in fill handler");
                }

                // performing action
                FillService service = new FillService();
                FillResult result = service.fill(fillRequest);

                // sending response
                Gson gson = new Gson();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream resBody = exchange.getResponseBody();
                String jsonResult = gson.toJson(result);
                IO.writeString(jsonResult, resBody);
                resBody.close();

                success = true;
            }

            if (!success) {
                throw new BadRequestException("Failed to fill db in http handler");
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        } catch (BadRequestException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
}