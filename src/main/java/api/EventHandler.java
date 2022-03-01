package api;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import results.EventResult;
import results.EventsResult;
import service.EventService;
import util.IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
                throw new BadMethodException();
            }
            Headers reqHeaders = exchange.getRequestHeaders();
            if (!reqHeaders.containsKey("Authorization")) {
                throw new BadRequestException("Authorization is required");
            }

            String authToken = reqHeaders.getFirst("Authorization");

            String urlPath = exchange.getRequestURI().toString();
            String[] params = urlPath.split("[/]"); // return ["","person","personID"]

            EventService eventService = new EventService();
            Gson gson = new Gson();

            // performing service
            EventsResult eventsResult = null;
            EventResult eventResult = null;
            if (params.length == 2) {
                eventsResult = eventService.getEvents(authToken);
                if (eventsResult.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
            } else if (params.length == 3) {
                eventResult = eventService.getEvent(authToken, params[2]);
                if (eventResult.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
            } else {
                throw new BadRequestException("incorrect num of params");
            }

            OutputStream resBody = exchange.getResponseBody();

            String jsonResult;
            if (eventsResult != null) {
                jsonResult = gson.toJson(eventsResult);
            } else {
                jsonResult = gson.toJson(eventResult);
            }

            if (jsonResult == null) {
                throw new IOException();
            }

            // sending response
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