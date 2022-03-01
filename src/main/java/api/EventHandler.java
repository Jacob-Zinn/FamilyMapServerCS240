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

        boolean success = false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {

                    String authToken = reqHeaders.getFirst("Authorization");

                    String urlPath=exchange.getRequestURI().toString();
                    String[] params = urlPath.split("[/]"); // return [IBM, Intel, HP, Cisco]

                    EventService eventService = new EventService();
                    Gson gson=new Gson();

                    // performing service
                    EventsResult eventsResult = null;
                    EventResult eventResult = null;
                    if (params.length == 1) {
                        eventsResult = eventService.getEvents(authToken);
                    } else if (params.length == 2) {
                        eventResult = eventService.getEvent(authToken, params[1]);
                    } else {
                        throw new BadRequestException("incorrect num of params");
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream resBody=exchange.getResponseBody();

                    String jsonResult = null;
                    if (eventsResult != null) {
                        jsonResult = gson.toJson(eventsResult);
                    } else if (eventResult != null) {
                        jsonResult = gson.toJson(eventResult);
                    }

                    if (jsonResult == null) {
                        throw new IOException();
                    }

                    // sending response
                    IO.writeString(jsonResult, resBody);
                    resBody.close();

                    success = true;
                }
            }

            if (!success) {
                throw new BadRequestException("error: bad request in eventHandler");
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        } catch (BadRequestException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            e.printStackTrace();
        }
    }
}