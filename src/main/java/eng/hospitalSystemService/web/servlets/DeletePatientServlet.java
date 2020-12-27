package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PatientService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePatientServlet",urlPatterns = "/deletePatient")
public class DeletePatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PatientService patientService = new PatientService();

        String patientBirthNumberString = request.getParameter("patientBN");

        int patientBirthNumber = Integer.parseInt(patientBirthNumberString);



        if(patientService.get(patientBirthNumber)==null)alertService.add(Alert.Type.danger,"Patient was already deleted");
        else
        {
        try {
            patientService.delete(patientBirthNumber);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to delete patient through servlet",e);
        }
            alertService.add(Alert.Type.success, "User has been deleted.");
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
