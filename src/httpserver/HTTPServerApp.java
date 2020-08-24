package httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import handlers.Calculator;
import handlers.FileServer;
import handlers.Greeter;

public class HTTPServerApp {

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(9090), 0);
		httpServer.createContext("/hello", new Greeter());
		httpServer.createContext("/file", new FileServer());
		httpServer.createContext("/compute", new Calculator());
		httpServer.start();
	}

}
