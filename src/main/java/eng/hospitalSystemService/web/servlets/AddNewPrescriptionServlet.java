package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PrescriptionService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewPrescriptionServlet",urlPatterns = "/addNewPrescription")
public class AddNewPrescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PrescriptionService prescriptionService = new PrescriptionService();

        String nameOfMedicament = request.getParameter("nameOfMedicament");
        String usageOfMedicament = request.getParameter("usageOfMedicament");
        String birthNumberOfPatientString = request.getParameter("birthNumberOfPatient");

        int birthNumberOfPatient = Integer.parseInt(birthNumberOfPatientString);

        try {
            prescriptionService.create(nameOfMedicament,usageOfMedicament,birthNumberOfPatient);
        }catch (Exception e)
        {
            throw new RuntimeException("Unable to create prescription via Servlet",e);
        }

        alertService.add(Alert.Type.success, "Prescription successfully added");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
