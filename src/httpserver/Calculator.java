package httpserver;
import java.io.*;
import java.util.*;
import com.sun.net.httpserver.*;

public class Calculator implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {

		String operation = t.getRequestURI().getPath().replace("/compute/", "");
//		System.out.println("operation: " + operation);
//		System.out.println("query: " + t.getRequestURI().getQuery());
		String[] paramPairs = t.getRequestURI().getQuery().split("\\&");
		Map<String, String> params = new LinkedHashMap<String, String>();

		for (String pp : paramPairs)
			params.put(pp.substring(0, pp.indexOf('=')), pp.substring(pp.indexOf('=') + 1));

		StringBuilder sb = new StringBuilder();
		if (operation.equals("add")) {
			int n1 = Integer.parseInt(params.get("n1"));
			int n2 = Integer.parseInt(params.get("n2"));
			int sum = n1 + n2;

			sb.append("<html>\n");
			sb.append("<head><title>Summation Result</title></head>\n");
			sb.append("<body>\n");
			sb.append(n1 + " + " + n2 + " = " + sum + "\n");
			sb.append("</body>\n");
			sb.append("</html>");
			String result = sb.toString();

			t.sendResponseHeaders(200, result.getBytes().length);
			Headers headers = t.getResponseHeaders();
			headers.add("Date", Calendar.getInstance().getTime().toString());
			headers.add("Content-Type", "text/html");
			OutputStream os = t.getResponseBody();
			os.write(result.getBytes());
			os.close();
		}
	}
}