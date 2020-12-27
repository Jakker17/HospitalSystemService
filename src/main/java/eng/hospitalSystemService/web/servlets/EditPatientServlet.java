package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PatientService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditPatientServlet",urlPatterns = "/editPatient")
public class EditPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);

        String patientName = request.getParameter("patientName");
        String patientSurname= request.getParameter("patientSurname");
        String patientBirthNumberString= request.getParameter("patientBirthNumber");
        String patientAnamnesis= request.getParameter("anamnesis");
        String patientMedicamentsString= request.getParameter("medicaments");
        String patientRoomIdString= request.getParameter("room");
        String patientNursingStaffBirthNumberString= request.getParameter("nursingStaff");

        int patientBirthNumber= Integer.parseInt(patientBirthNumberString);
        int patientMedicaments= Integer.parseInt(patientMedicamentsString);
        int patientRoomId= Integer.parseInt(patientRoomIdString);
        int patientNursingStaffBirthNumber= Integer.parseInt(patientNursingStaffBirthNumberString);

        try {
            PatientService patientService = new PatientService();
            patientService.update(patientName,patientSurname,patientBirthNumber,patientAnamnesis,patientMedicaments,patientRoomId,patientNursingStaffBirthNumber);
        } catch (Exception e){
            throw new RuntimeException("failed to update Patient through Servlet.",e);
        }
        alertService.add(Alert.Type.success,"Patient have been updated.");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
