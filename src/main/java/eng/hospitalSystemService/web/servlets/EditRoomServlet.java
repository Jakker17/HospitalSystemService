package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditRoomServlet",urlPatterns = "/editRoom")
public class EditRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        RoomService roomService = new RoomService();
        DepartmentService departmentService = new DepartmentService();

        String roomIdString = request.getParameter("roomID");
        String roomCapacityString = request.getParameter("capacity");
        String departmentIdString = request.getParameter("departmentID");

        if(patternCheckService.doPatternCheck("[^0-9]", roomCapacityString)) alertService.add(Alert.Type.danger, "Room Capacity can obtain only Numbers.");
        else if(patternCheckService.doPatternCheck("[^0-9]", departmentIdString)) alertService.add(Alert.Type.danger, "DepartmentID can obtain only Numbers.");
        else if(roomCapacityString.length()>1)alertService.add(Alert.Type.danger,"Room capacity can be only from 0-9");
        else if(roomCapacityString.equals(""))alertService.add(Alert.Type.danger,"Room Capacity cannot be empty");
        else if(departmentIdString.equals(""))alertService.add(Alert.Type.danger,"Department ID cannot be empty");
        else if(departmentService.get(Integer.parseInt(departmentIdString))==null)alertService.add(Alert.Type.danger,"There is no department with this ID");
        else
        {
            int roomId = Integer.parseInt(roomIdString);
            int roomCapacity = Integer.parseInt(roomCapacityString);
            int departmentId = Integer.parseInt(departmentIdString);

                roomService.update(roomId, roomCapacity, departmentId);
                alertService.add(Alert.Type.success, "Room has been updated successfully.");
                response.sendRedirect("listOfRooms.jsp");
        }
        response.sendRedirect("editRoom.jsp?roomid="+roomIdString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
