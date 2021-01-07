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
        RoomService roomService = new RoomService();

        String patientName = request.getParameter("patientName");
        String patientSurname= request.getParameter("patientSurname");
        String patientBirthNumberString= request.getParameter("patientBirthNumber");
        String patientAnamnesis= request.getParameter("anamnesis");
        String patientRoomIdString= request.getParameter("room");

        String patientNursingStaffSurname = request.getParameter("nursingStaff");

        if(patientService.get(patientBirthNumberString)==null){
            alertService.add(Alert.Type.danger,"Pecient neexistuje.");
            response.sendRedirect("mainPage.jsp");
        }

        int personalBirthNumber = personalService.getPersonalBirthNumberBySurname(patientNursingStaffSurname);
        if (personalService.get(personalBirthNumber)==null){
            alertService.add(Alert.Type.danger,"Ošetřovatel s tímto příjmením neexistuje.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        if(patternCheckService.doPatternCheck("[^0-9]", patientRoomIdString)){
            alertService.add(Alert.Type.danger,"ID pokoje musí obsahovat jen čísla.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }
        if (patientRoomIdString.equals(""))
        {alertService.add(Alert.Type.danger,"ID pokoje nesmí být prázdné.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        if (patientRoomIdString.length()>5)
        {alertService.add(Alert.Type.danger,"ID pokoje nesmí být delší než 5 čísel.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        if (roomService.getRoom(Integer.parseInt(patientRoomIdString))==null)
        {alertService.add(Alert.Type.danger,"Tento pokoj neexistuje.");
        response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        if (!roomService.checkAvailiabilityOfRoom(Integer.parseInt(patientRoomIdString)))
        {alertService.add(Alert.Type.danger,"Na tomto pokoji již není místo.");
            response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
        }

        int patientBirthNumber= Integer.parseInt(patientBirthNumberString);
        int patientRoomId= Integer.parseInt(patientRoomIdString);
        if (!personalService.get(personalBirthNumber).getProffesion().equals("Zdravotnicky Personal"))
        {
            alertService.add(Alert.Type.danger,"Tento personal není zdravotník a proto nemůže být přiřazen.");
        }
        else if (patientAnamnesis.equals(""))alertService.add(Alert.Type.danger,"Anamnéza nesmí být prázdná.");
        else if (patientAnamnesis.length()>255)alertService.add(Alert.Type.danger,"Anamnéza nesmí být delší než 255 znaků.");
        else {
            try {

                patientService.update(patientName, patientSurname, patientBirthNumber, patientAnamnesis, patientRoomId, personalBirthNumber);
            } catch (Exception e) {
                throw new RuntimeException("failed to update Patient through Servlet.", e);
            }
            alertService.add(Alert.Type.success, "Pacient upraven.");
            response.sendRedirect("mainPage.jsp");
        }
        response.sendRedirect("editPatient.jsp?pacientBirthNumber="+patientBirthNumberString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
