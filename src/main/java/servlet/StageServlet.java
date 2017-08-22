package servlet;

import ticket.MyTicketService;
import tools.JSONObject;
import tools.Logger;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="MyStage", urlPatterns={"/stage"})
public class StageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ServletOutputStream out = resp.getOutputStream();
        JSONObject json = new JSONObject();
        StringBuilder sb = new StringBuilder();
        int[][] availableSeats = MyTicketService.getInstance().getAvailableSeats();
        for (int i = 0; i < availableSeats.length - 1; i++) {
            int[] row = availableSeats[i];
            sb.append(Arrays.toString(row));
            sb.append(", ");
        }
        sb.append(Arrays.toString(availableSeats[availableSeats.length - 1]));
        json.add("stage", sb.toString());
        out.write(json.toString().getBytes());
        out.flush();
        out.close();
    }

}
