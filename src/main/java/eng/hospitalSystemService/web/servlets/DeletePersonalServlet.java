package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PersonalService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonalServlet",urlPatterns = "/deletePersonal")
public class DeletePersonalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String birthNumberOfPersonalString = request.getParameter("birthNumber");
        int birthNumberOfPersonal = Integer.parseInt(birthNumberOfPersonalString);

        PersonalService personalService = new PersonalService();
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        if(personalService.get(birthNumberOfPersonal)==null)alertService.add(Alert.Type.danger,"User not found or already deleted.");
        else {

            try {
                personalService.delete(birthNumberOfPersonal);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete personal via Servlet.", e);
            }
            alertService.add(Alert.Type.success, "User has been deleted.");
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Unauthorized access.");
response.sendRedirect("index.jsp");
    }
}
