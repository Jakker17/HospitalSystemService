package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditPatientServlet",urlPatterns = "/editPatient")
public class EditPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PersonalService personalService = new PersonalService();

        String patientName = request.getParameter("patientName");
        String patientSurname= request.getParameter("patientSurname");
        String patientBirthNumberString= request.getParameter("patientBirthNumber");
        String patientAnamnesis= request.getParameter("anamnesis");
        String patientMedicamentsString= request.getParameter("medicaments");
        String patientRoomIdString= request.getParameter("room");
        String patientNursingStaffBirthNumberString= request.getParameter("nursingStaff");

        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(patientNursingStaffBirthNumberString);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter){
            alertService.add(Alert.Type.danger,"Nursing staff birthNumber can contain only Numbers.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        pattern = Pattern.compile("[^0-9]");
        matcher = pattern.matcher(patientRoomIdString);
        isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter){
            alertService.add(Alert.Type.danger,"RoomID can contain only Numbers.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        pattern = Pattern.compile("[^0-9]");
        matcher = pattern.matcher(patientMedicamentsString);
        isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter){
            alertService.add(Alert.Type.danger,"Medicaments can contain only Numbers.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        int patientBirthNumber= Integer.parseInt(patientBirthNumberString);
        int patientMedicaments= Integer.parseInt(patientMedicamentsString);
        int patientRoomId= Integer.parseInt(patientRoomIdString);
        int patientNursingStaffBirthNumber= Integer.parseInt(patientNursingStaffBirthNumberString);

        if (personalService.get(patientNursingStaffBirthNumber)==null)alertService.add(Alert.Type.danger,"Not found Personal with this Birth ID.");
        else if (patientAnamnesis.length()>255)alertService.add(Alert.Type.danger,"Anamnesis is too long, only 255 characters.");
        else {
            try {
                PatientService patientService = new PatientService();
                patientService.update(patientName, patientSurname, patientBirthNumber, patientAnamnesis, patientMedicaments, patientRoomId, patientNursingStaffBirthNumber);
            } catch (Exception e) {
                throw new RuntimeException("failed to update Patient through Servlet.", e);
            }
            alertService.add(Alert.Type.success, "Patient have been updated.");
            response.sendRedirect("index.jsp");
        }
        response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
