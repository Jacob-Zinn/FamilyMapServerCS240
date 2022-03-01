package api;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import results.PersonResult;
import results.PersonsResult;
import service.PersonService;
import util.IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {

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

                    PersonService personService = new PersonService();
                    Gson gson=new Gson();

                    // performing service
                    PersonsResult personsResult = null;
                    PersonResult personResult = null;
                    if (params.length == 1) {
                        personsResult = personService.getPersons(authToken);
                    } else if (params.length == 2) {
                        personResult = personService.getPerson(authToken, params[1]);
                    } else {
                        throw new BadRequestException("incorrect num of params");
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream resBody=exchange.getResponseBody();

                    String jsonResult = null;
                    if (personsResult != null) {
                       jsonResult = gson.toJson(personsResult);
                    } else if (personResult != null) {
                        jsonResult = gson.toJson(personResult);
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
                throw new BadRequestException("error: bad request in personHandler");
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