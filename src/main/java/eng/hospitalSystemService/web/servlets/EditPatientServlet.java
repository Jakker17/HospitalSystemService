package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

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
        PersonalService personalService = new PersonalService();
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        PatientService patientService = new PatientService();

        String patientName = request.getParameter("patientName");
        String patientSurname= request.getParameter("patientSurname");
        String patientBirthNumberString= request.getParameter("patientBirthNumber");
        String patientAnamnesis= request.getParameter("anamnesis");
        String patientRoomIdString= request.getParameter("room");
        String patientNursingStaffBirthNumberString= request.getParameter("nursingStaff");

        if(patternCheckService.doPatternCheck("[^0-9]", patientNursingStaffBirthNumberString)){
            alertService.add(Alert.Type.danger,"Nursing staff birthNumber can contain only Numbers.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        if(patternCheckService.doPatternCheck("[^0-9]", patientRoomIdString)){
            alertService.add(Alert.Type.danger,"RoomID can contain only Numbers.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }


        if(patientService.get(patientBirthNumberString)==null){
            alertService.add(Alert.Type.danger,"Patient must have been already deleted.");
            response.sendRedirect("mainPage.jsp");
        }

        int patientBirthNumber= Integer.parseInt(patientBirthNumberString);

        int patientRoomId= Integer.parseInt(patientRoomIdString);
        int patientNursingStaffBirthNumber= Integer.parseInt(patientNursingStaffBirthNumberString);

        if (personalService.get(patientNursingStaffBirthNumber)==null)alertService.add(Alert.Type.danger,"Not found Personal with this Birth ID.");
        else if (patientAnamnesis.equals(""))alertService.add(Alert.Type.danger,"Anamnesis cannot be empty.");
        else if (patientAnamnesis.length()>255)alertService.add(Alert.Type.danger,"Anamnesis is too long, only 255 characters.");
        else {
            try {

                patientService.update(patientName, patientSurname, patientBirthNumber, patientAnamnesis, patientRoomId, patientNursingStaffBirthNumber);
            } catch (Exception e) {
                throw new RuntimeException("failed to update Patient through Servlet.", e);
            }
            alertService.add(Alert.Type.success, "Patient have been updated.");
            response.sendRedirect("mainPage.jsp");
        }
        response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
