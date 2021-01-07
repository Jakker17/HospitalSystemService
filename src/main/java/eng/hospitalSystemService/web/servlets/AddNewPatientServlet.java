package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if (roomIDString.equals(""))roomIDString="0";

        int personalBirthNumber;
        personalBirthNumber = personalService.getPersonalBirthNumberBySurname(personalSurname);
        if (personalService.get(personalBirthNumber)==null){
            alertService.add(Alert.Type.danger,"Personal s tímto příjmením neexistuje");
        }
        else if (!personalService.get(personalBirthNumber).getProffesion().equals("Zdravotnicky Personal"))
        {
            alertService.add(Alert.Type.danger,"Tento personál není zdravotník a tudíž nemůže být přiřazen.");
        }

        else if (roomService.getRoom(roomIDString)==null){
            alertService.add(Alert.Type.danger, "Pokoj s tímto ID neexistuje");
        }

        else if(patternCheckService.doPatternCheck("[^a-zA-Z]",patientSurname)){
            alertService.add(Alert.Type.danger,"Příjmení nesmí obsahovat specílní znaky a čísla.");
        }

        else if(patternCheckService.doPatternCheck("[^0-9]",patientBirthNumberString)){
            alertService.add(Alert.Type.danger,"Rodné číslo může obsahovat jenom čísla.");
        }

        else if(patternCheckService.doPatternCheck("[^0-9]",roomIDString)){
            alertService.add(Alert.Type.danger,"ID pokoje může obsahovat jenom čísla.");
        }

        else if (personalService.get(patientBirthNumberString)!=null&&!personalService.get(patientBirthNumberString).getPersonSurname().equals(patientSurname)){
                alertService.add(Alert.Type.danger,"Existuje personal s tímto rodným číslem ale různým příjmením.");
        }
        else if(patientBirthNumberString.length()>6)alertService.add(Alert.Type.danger,"RČ nesmí být delší než 6 znaků.");
        else if(patientName.length()>60)alertService.add(Alert.Type.danger,"Jméno nesmí být delší než 60 znaků.");
        else if(patientSurname.length()>60)alertService.add(Alert.Type.danger,"Příjmení nesmí být delší než 60 znaků.");
        else if(personalSurname.equals(""))alertService.add(Alert.Type.danger,"Ošetřovatel nesmí být prázdný.");
        else if(patientSurname.equals(""))alertService.add(Alert.Type.danger, "Příjmení pacienta nesmí být prázdné");
        else if(patientService.get(patientBirthNumberString)!=null)alertService.add(Alert.Type.danger, "Pacient s tímto rodným číslem již existuje.");
        else if(patientBirthNumberString.equals(""))alertService.add(Alert.Type.danger,"Rodné číslo nesmí být prázdné");
        else if(anamnesis.length()>255)alertService.add(Alert.Type.danger,"Anamnéza nesmí být delší než 255 znaků.");
        else if(anamnesis.equals(""))alertService.add(Alert.Type.danger,"Anamnéza nesmí být prázdná");
        else
        {
            int patientBirthNumber = Integer.parseInt(patientBirthNumberString);
            int roomID = Integer.parseInt(roomIDString);

            if(!roomService.checkAvailiabilityOfRoom(roomID))alertService.add(Alert.Type.danger,"Na tomto pokoji již není místo.");
            else {
                try
                {
                    patientService.create(patientBirthNumber, patientName, patientSurname, anamnesis, personalBirthNumber, roomID);
                } catch (Exception e)
                {
                    throw new RuntimeException("Failed to store Patient via servlet");
                }
                alertService.add(Alert.Type.success, "Pacient vytvořen úspěšně.");
                response.sendRedirect("wantToAddNewPrescription.jsp?patientBN="+patientBirthNumber);
            }
        }
        response.sendRedirect("addNewPatient.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
