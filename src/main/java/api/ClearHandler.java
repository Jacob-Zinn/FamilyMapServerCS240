package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import service.ClearService;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success=false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                ClearService clearService = new ClearService();
                clearService.clear();

                success = true;
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
}