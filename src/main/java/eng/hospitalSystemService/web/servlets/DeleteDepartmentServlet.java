package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;
import eng.hospitalSystemService.db.entities.PersonalEntity;
import eng.hospitalSystemService.db.entities.PokojEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteDepartmentServlet",urlPatterns = "/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);

        String departmentIDString = request.getParameter("departmentID");
        int departmentID = Integer.parseInt(departmentIDString);

        DepartmentService departmentService = new DepartmentService();
        if(departmentService.get(departmentID)==null)
        {
            alertService.add(Alert.Type.danger,"Department was already deleted.");
            response.sendRedirect("listOfDepartments.jsp");
        }
        else {
            try {
                PersonalService personalService = new PersonalService();
                List<PersonalEntity> personalEntityList = personalService.getListOfPersonal();
                for (PersonalEntity personalEntity: personalEntityList) {
                    if (personalEntity.getDepartment()==departmentID){
                        alertService.add(Alert.Type.danger, "There are assigned Personals please reassign them before deleting department.");
                    }
                }
            }
            catch (Exception e){
                throw new RuntimeException("Failed to delete due Personal in this department.",e);
            }

            try {
                RoomService roomService = new RoomService();
                List<PokojEntity> rooms = roomService.getAllRooms();
                for (PokojEntity pokoj: rooms) {
                    if (pokoj.getDepartmentid()==departmentID)
                        alertService.add(Alert.Type.danger, "There are assigned Rooms please reassign them before deleting department.");
                }
            }catch (Exception e){throw new RuntimeException("Failed to delete due rooms in this department.");}

            try {
                departmentService.delete(departmentID);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete department via Servlet.", e);
            }
        }
        response.sendRedirect("deletedDep.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
