package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PatientService;
import eng.hospitalSystemService.app.PersonalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewPatientServlet",urlPatterns = "/addNewPatient")
public class AddNewPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlertService alertService = new AlertService();
        PatientService patientService = new PatientService();

        String patientBirthNumberString = request.getParameter("patientBirthNumber");
        String patientName = request.getParameter("patientName");
        String patientSurname = request.getParameter("patientSurname");
        String anamnesis = request.getParameter("anamnesis");
        String medicamentsString = request.getParameter("medicaments");
        String personalBirthNumberString = request.getParameter("personalSurname");
        String roomIDString = request.getParameter("roomID");

        int patientBirthNumber = Integer.parseInt(patientBirthNumberString);
        int roomID = Integer.parseInt(roomIDString);
        int medicaments = Integer.parseInt(medicamentsString);

        PersonalService personalService = new PersonalService();
        if (personalService.get(personalBirthNumberString)==null){
            alertService.add(Alert.Type.danger, "Personal by this Surname was not found.");
            response.sendRedirect("addNewPatient.jsp");
        }
        int personalBirthNumber = Integer.parseInt(personalBirthNumberString);


        patientService.create(patientBirthNumber,patientName,patientSurname,anamnesis,medicaments,personalBirthNumber,roomID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = new AlertService();
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
