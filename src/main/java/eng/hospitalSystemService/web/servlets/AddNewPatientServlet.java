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

@WebServlet(name = "AddNewPatientServlet",urlPatterns = "/addNewPatient")
public class AddNewPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PatientService patientService = new PatientService();
        PersonalService personalService = new PersonalService();
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        RoomService roomService = new RoomService();

        String patientBirthNumberString = request.getParameter("patientBirthNumber");
        String patientName = request.getParameter("patientName");
        String patientSurname = request.getParameter("patientSurname");
        String anamnesis = request.getParameter("anamnesis");
        String personalSurname = request.getParameter("personalSurname");
        String roomIDString = request.getParameter("roomID");

        int personalBirthNumber;
        personalBirthNumber = personalService.getPersonalBirthNumberBySurname(personalSurname);
        if (personalService.get(personalBirthNumber)==null){
            alertService.add(Alert.Type.danger,"There is no personal with this surname.");
            response.sendRedirect("addNewPatient.jsp");
        }

        if (roomService.getRoom(roomIDString)==null){
            alertService.add(Alert.Type.danger, "There is no room with this ID.");
        }

        if (roomIDString.equals(""))roomIDString="0";
        if(patternCheckService.doPatternCheck("[^a-zA-Z]",patientSurname)){
            alertService.add(Alert.Type.danger,"Surname cannot contain numbers and special characters.");
            response.sendRedirect("addNewPatient.jsp");
        }

        if(patternCheckService.doPatternCheck("[^0-9]",patientBirthNumberString)){
            alertService.add(Alert.Type.danger,"BirthNumber can contain only Numbers.");
            response.sendRedirect("addNewPatient.jsp");
        }

        if(patternCheckService.doPatternCheck("[^0-9]",roomIDString)){
            alertService.add(Alert.Type.danger,"Room number can contain only Numbers.");
            response.sendRedirect("addNewPatient.jsp");
        }

        if (personalService.get(patientBirthNumberString)!=null){
            if (!personalService.get(patientBirthNumberString).getPersonSurname().equals(patientSurname))
            {
                alertService.add(Alert.Type.danger,"there is already Personal with this number but different name");
            }
        }
        else if(personalSurname.equals(""))alertService.add(Alert.Type.danger,"Personal cannot be empty.");
        else if(patientSurname.equals(""))alertService.add(Alert.Type.danger, "Patient Surname cannot be empty.");
        else if(patientService.get(patientBirthNumberString)!=null)alertService.add(Alert.Type.danger, "Patient with this Birth number already exists.");
        else if(patientBirthNumberString.equals(""))alertService.add(Alert.Type.danger,"Birth Number of patient cannot be empty.");
        else if(anamnesis.length()>255)alertService.add(Alert.Type.danger,"Anamnesis cannot be longer then 255 characters.");
        else if(anamnesis.equals(""))alertService.add(Alert.Type.danger,"Anamnesis cannot be empty.");
        else
        {
            int patientBirthNumber = Integer.parseInt(patientBirthNumberString);
            int roomID = Integer.parseInt(roomIDString);

            if (patientBirthNumber < 100000 || patientBirthNumber > 999999)alertService.add(Alert.Type.danger, "patient birthNumber can be only within 100000 and 999999 ");
            else if(!roomService.checkAvailiabilityOfRoom(roomID))alertService.add(Alert.Type.danger,"At this room is no Space, select other room.");
            else {
                try
                {
                    patientService.create(patientBirthNumber, patientName, patientSurname, anamnesis, personalBirthNumber, roomID);
                } catch (Exception e)
                {
                    throw new RuntimeException("Failed to store Patient via servlet");
                }
                alertService.add(Alert.Type.success, "Patient successfully added.");
                response.sendRedirect("index.jsp");
            }
        }
        response.sendRedirect("addNewPatient.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
