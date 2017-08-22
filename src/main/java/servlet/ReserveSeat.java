package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tools.JSONObject;

@WebServlet(name="ReserveSeat", urlPatterns={"/reserve"})
public class ReserveSeat extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("data: " + data);

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(data).getAsJsonObject();
        System.out.println(json);

        resp.setContentType("application/json");
        ServletOutputStream out = resp.getOutputStream();
        out.write("{\"status\": \"ok\"}".getBytes());
        out.flush();
        out.close();
    }
}
