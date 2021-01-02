package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.RoomService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRoomServlet", urlPatterns = "/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        RoomService roomService = new RoomService();

        String roomIDString = request.getParameter("roomID");

        if (roomService.getRoom(roomIDString)==null){
            alertService.add(Alert.Type.danger,"Room was already deleted or not found.");
            response.sendRedirect("listOfRooms.jsp");
        }

        if(!roomService.isRoomEmpty(Integer.parseInt(roomIDString))) {
            alertService.add(Alert.Type.danger, "Room is not empty, please re-assign all patients from this room");
            response.sendRedirect("listOfRooms.jsp");
        }

        roomService.delete(Integer.parseInt(roomIDString));
        alertService.add(Alert.Type.success,"Room was deleted successfully.");
        response.sendRedirect("listOfRooms.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
