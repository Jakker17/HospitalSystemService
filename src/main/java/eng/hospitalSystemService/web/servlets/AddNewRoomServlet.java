package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewRoomServlet", urlPatterns = "/addNewRoom")
public class AddNewRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        RoomService roomService = new RoomService();
        DepartmentService departmentService = new DepartmentService();

        String roomIdString = request.getParameter("roomID");
        String roomCapacityString = request.getParameter("capacityOfRoom");
        String departmentIdString = request.getParameter("departmentID");

        if (patternCheckService.doPatternCheck("[^0-9]", roomIdString)) alertService.add(Alert.Type.danger, "Room ID can obtain only Numbers.");
        else if(patternCheckService.doPatternCheck("[^0-9]", roomCapacityString)) alertService.add(Alert.Type.danger, "Room Capacity can obtain only Numbers.");
        else if(patternCheckService.doPatternCheck("[^0-9]", departmentIdString)) alertService.add(Alert.Type.danger, "DepartmentID can obtain only Numbers.");
        else if(roomCapacityString.length()>1)alertService.add(Alert.Type.danger,"Room capacity can be only from 0-9");
        else if(roomIdString.equals(""))alertService.add(Alert.Type.danger,"Room ID cannot be empty");
        else if(roomCapacityString.equals(""))alertService.add(Alert.Type.danger,"Room Capacity cannot be empty");
        else if(departmentIdString.equals(""))alertService.add(Alert.Type.danger,"Department ID cannot be empty");
        else if (departmentService.get(departmentIdString)== null)alertService.add(Alert.Type.danger,"There is no department with this ID");
        else if (roomService.getRoom(roomIdString)!=null)alertService.add(Alert.Type.danger,"There is already room with this ID. ");
        else
            {
                int roomId = Integer.parseInt(roomIdString);
                int roomCapacity = Integer.parseInt(roomCapacityString);
                int departmentId = Integer.parseInt(departmentIdString);

                if (roomId>9999||roomId<0)alertService.add(Alert.Type.danger,"Room ID number can be only within range 0 - 9999");
                else
                    {
                        roomService.create(roomId, roomCapacity, departmentId);
                        alertService.add(Alert.Type.success, "Room has been added successfully.");
                        response.sendRedirect("index.jsp");
                    }
                response.sendRedirect("addNewRoom.jsp");
            }
        response.sendRedirect("addNewRoom.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access");
        response.sendRedirect("index.jsp");
    }
}
