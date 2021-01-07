package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonalServlet",urlPatterns = "/deletePersonal")
public class DeletePersonalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PersonalService personalService = new PersonalService();
        PatientService patientService = new PatientService();

        String birthNumberOfPersonalString = request.getParameter("birthNumberDelete");
        int birthNumberOfPersonal = Integer.parseInt(birthNumberOfPersonalString);

        if(patientService.isPatientAssignedToThisStaff(birthNumberOfPersonal))
        {
            alertService.add(Alert.Type.danger,"Není možné smazat zaměstnance s přiřazenýma pacientama.");
            response.sendRedirect("listOfEmployees.jsp");
        }
        if(birthNumberOfPersonal==999999){
            alertService.add(Alert.Type.danger,"Nejde smazat tohoto uživatele, jakožto hlavního admina.");
            response.sendRedirect("listOfEmployees.jsp");
        }

        if(personalService.get(birthNumberOfPersonal)==null)alertService.add(Alert.Type.danger,"Uživatel nenalezen.");
        else {

            try {
                personalService.delete(birthNumberOfPersonal);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete personal via Servlet.", e);
            }
            alertService.add(Alert.Type.success, "Uživatel smazán.");

        }
        response.sendRedirect("listOfEmployees.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
