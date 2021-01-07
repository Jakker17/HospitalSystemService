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
            alertService.add(Alert.Type.danger,"Rodné číslo pacienta musí být číslo");
            response.sendRedirect("addNewPrescription.jsp");
        }
        if (nameOfMedicament.length()>60)alertService.add(Alert.Type.danger,"Pro název léku použijte jen 60 písmen.");
        else if (nameOfMedicament.equals(""))alertService.add(Alert.Type.danger, "Jméno léku nesmí být prázdné.");
        else if (usageOfMedicament.length()>255)alertService.add(Alert.Type.danger,"Pro použítí léku použijte jen 255písmen.");
        else if(birthNumberOfPatientString.equals(""))alertService.add(Alert.Type.danger, "Rodné číslo nesmí být prázdné.");
        else if (patientService.get(birthNumberOfPatientString)==null)alertService.add(Alert.Type.danger,"Pacient s tímto rodným číslem neexistuje.");
        else{
        int birthNumberOfPatient = Integer.parseInt(birthNumberOfPatientString);

        try {
            prescriptionService.create(nameOfMedicament,usageOfMedicament,birthNumberOfPatient);
        }catch (Exception e)
        {
            throw new RuntimeException("Unable to create prescription via Servlet",e);
        }

        alertService.add(Alert.Type.success, "Předpis úspěšně vytvořen.");
        response.sendRedirect("listOfPatients.jsp");
        }
        response.sendRedirect("addNewPrescription.jsp?patientBN="+birthNumberOfPatientString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
