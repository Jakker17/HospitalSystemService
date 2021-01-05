package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

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
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        PatientService patientService = new PatientService();

        String nameOfMedicament = request.getParameter("nameOfMedicament");
        String usageOfMedicament = request.getParameter("usageOfMedicament");
        String birthNumberOfPatientString = request.getParameter("birthNumberOfPatient");

        if (patternCheckService.doPatternCheck("[^0-9]",birthNumberOfPatientString)){
            alertService.add(Alert.Type.danger,"Birth Number of Patient must be a number.");
            response.sendRedirect("addNewPrescription.jsp");
        }
        if (nameOfMedicament.length()>60)alertService.add(Alert.Type.danger,"Too long name of Medicament, use only 60 letters.");
        else if (nameOfMedicament.equals(""))alertService.add(Alert.Type.danger, "Name of medicament cannot be empty.");
        else if (usageOfMedicament.length()>255)alertService.add(Alert.Type.danger,"Too long usage of medicament, use only 255 letters.");
        else if(birthNumberOfPatientString.equals(""))alertService.add(Alert.Type.danger, "Birth number of patient cannot be null.");
        else if (patientService.get(birthNumberOfPatientString)==null)alertService.add(Alert.Type.danger,"Patient with this Number does not exist.");
        else{
        int birthNumberOfPatient = Integer.parseInt(birthNumberOfPatientString);

        try {
            prescriptionService.create(nameOfMedicament,usageOfMedicament,birthNumberOfPatient);
        }catch (Exception e)
        {
            throw new RuntimeException("Unable to create prescription via Servlet",e);
        }

        alertService.add(Alert.Type.success, "Prescription successfully added");
        response.sendRedirect("listOfPatients.jsp");
        }
        response.sendRedirect("addNewPrescription.jsp?patientBN="+birthNumberOfPatientString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
