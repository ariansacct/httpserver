package handlers;

import java.io.*;
import java.util.*;
import com.sun.net.httpserver.*;

public class Calculator implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {

		String operation = t.getRequestURI().getPath().replace("/compute/", "");
		String[] paramPairs = t.getRequestURI().getQuery().split("\\&");
		Map<String, String> params = new LinkedHashMap<String, String>();

		for (String pp : paramPairs)
			params.put(pp.substring(0, pp.indexOf('=')), pp.substring(pp.indexOf('=') + 1));

		StringBuilder sb = new StringBuilder();
		int result = 0;
		String operand = null;

		int n1 = Integer.parseInt(params.get("n1"));
		int n2 = Integer.parseInt(params.get("n2"));

		if (operation.equals("add")) {
			operand = "+";
			result = n1 + n2;
		}
		else if (operation.equals("sub")) {
			operand = "-";
			result = n1 - n2;
		}
		else if (operation.equals("mul")) {
			operand = "*";
			result = n1 * n2;
		}
		else if (operation.equals("div")) {
			operand = "/";
			result = n1 / n2;
		}

		sb.append("<html>\n");
		sb.append("<head><title>Result</title></head>\n");
		sb.append("<body>\n");
		sb.append(n1 + " " + operand + " " + n2 + " = " + result + "\n");
		sb.append("</body>\n");
		sb.append("</html>");
		String output = sb.toString();

		t.sendResponseHeaders(200, output.getBytes().length);
		Headers headers = t.getResponseHeaders();
		headers.add("Date", Calendar.getInstance().getTime().toString());
		headers.add("Content-Type", "text/html");
		OutputStream os = t.getResponseBody();
		os.write(output.getBytes());
		os.close();
	}
}