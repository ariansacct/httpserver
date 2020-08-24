package handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Greeter implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String response = "Hello, how are you today?";
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(response.getBytes());
		responseBody.close();
	}

}
