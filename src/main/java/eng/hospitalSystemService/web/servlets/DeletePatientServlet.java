package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

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
        PrescriptionService prescriptionService = new PrescriptionService();

        String patientBirthNumberString = request.getParameter("patientBN");

        int patientBirthNumber = Integer.parseInt(patientBirthNumberString);


        if(patientService.get(patientBirthNumber)==null)alertService.add(Alert.Type.danger,"Pacient nenalezen.");
        else
        {
        try {
            prescriptionService.deleteByPatient(patientBirthNumber);
            patientService.delete(patientBirthNumber);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to delete patient through servlet",e);
        }
            alertService.add(Alert.Type.success, "Pacient smazán.");
        }
        response.sendRedirect("listOfPatients.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
