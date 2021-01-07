package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractList;

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

        if (patternCheckService.doPatternCheck("[^0-9]", roomIdString)) alertService.add(Alert.Type.danger, "ID pokoje může obsahovat pouze čísla.");
        else if(patternCheckService.doPatternCheck("[^0-9]", roomCapacityString)) alertService.add(Alert.Type.danger, "Kapacita pokoje může obsahovat pouze čísla.");
        else if(patternCheckService.doPatternCheck("[^0-9]", departmentIdString)) alertService.add(Alert.Type.danger, "ID oddělení může obsahovat pouze čísla.");
        else if(roomIdString.length()>5)alertService.add(Alert.Type.danger,"Neplatná délka ID pokoje, maximálně 5 čísel.");
        else if(roomCapacityString.length()>5)alertService.add(Alert.Type.danger,"Neplatná délka kapacity pokoje, maximálně 5 čísel.");
        else if(departmentIdString.length()>5)alertService.add(Alert.Type.danger,"Neplatná délka ID oddělení, maximálně 5 čísel.");
        else if(roomCapacityString.length()>1)alertService.add(Alert.Type.danger,"Pokoj může mít kapacitu pouze v rozsahu 0-9.");
        else if(roomIdString.equals(""))alertService.add(Alert.Type.danger,"ID pokoje nesmí být prázdné");
        else if(roomCapacityString.equals(""))alertService.add(Alert.Type.danger,"Kapacita pokoje nesmí být prázdná.");
        else if(departmentIdString.equals(""))alertService.add(Alert.Type.danger,"ID oddělení nesmí být prázdné.");
        else if (departmentService.get(departmentIdString)== null)alertService.add(Alert.Type.danger,"Oddělení s tímto ID neexistuje");
        else if (roomService.getRoom(roomIdString)!=null)alertService.add(Alert.Type.danger,"Pokoj s tímto ID už existuje. ");
        else
            {
                int roomId = Integer.parseInt(roomIdString);
                int roomCapacity = Integer.parseInt(roomCapacityString);
                int departmentId = Integer.parseInt(departmentIdString);


                if (roomId>9999||roomId<0)alertService.add(Alert.Type.danger,"ID pokoje musí být v rozsahu 0-999");
                else if(roomCapacity==0) alertService.add(Alert.Type.danger,"Kapacita nesmí být 0");
                else
                    {
                        roomService.create(roomId, roomCapacity, departmentId);
                        alertService.add(Alert.Type.success, "Pokoj byl přidán úspěšně");
                        response.sendRedirect("mainPage.jsp");
                    }
                response.sendRedirect("addNewRoom.jsp");
            }
        response.sendRedirect("addNewRoom.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
