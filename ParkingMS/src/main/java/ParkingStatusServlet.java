import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/parking-status")

public class ParkingStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try (Connection connection = DatabaseConnector.getConnection()) {
//            String sql = "SELECT slot_number, available FROM parking_data";
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    response.setContentType("text/html");
//                    PrintWriter out = response.getWriter();
//                    out.println("<html><body>");
//                    out.println("<h1>Parking Slot Status</h1>");
//                    out.println("<table>");
//                    out.println("<tr><th>Slot Number</th><th>Status</th></tr>");
//                    while (resultSet.next()) {
//                        int slotNumber = resultSet.getInt("slot_number");
//                        b`oolean available = resultSet.getBoolean("available");
//                        out.println("<tr><td>" + slotNumber + "</td><td>" + (available ? "Available" : "Occupied") + "</td></tr>");
//                    }
//                    out.println("</table>");
//                    out.println("</body></html>");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int slotNumber = Integer.parseInt(request.getParameter("slot_number"));
        boolean available = Boolean.parseBoolean(request.getParameter("available"));

//        try (Connection connection = DatabaseConnector.getConnection()) {
//            String sql = "INSERT INTO parking_data (slot_number, available) VALUES (?, ?) ON CONFLICT (slot_number) DO UPDATE SET available = EXCLUDED.available";
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                statement.setInt(1, slotNumber);
//                statement.setBoolean(2, available);
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        System.out.println(slotNumber);
        System.out.println(available);
        
    }
}
