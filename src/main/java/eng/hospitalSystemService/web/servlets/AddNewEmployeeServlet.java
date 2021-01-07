package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewEmployeeServlet",urlPatterns = "/addNewEmployee")
public class AddNewEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PersonalService personalService = new PersonalService();
        DepartmentService departmentService = new DepartmentService();
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();
        PatientService patientService = new PatientService();

        String personalNumberString = request.getParameter("birthNumber");
        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String departmentString = request.getParameter("department");
        String profession = request.getParameter("profession");
        String password = request.getParameter("password");



        if(patternCheckService.doPatternCheck("[^0-9]",departmentString)){
            alertService.add(Alert.Type.danger,"ID oddělení musí obsahovat jen čísla.");
            response.sendRedirect("addNewEmployee.jsp");
        }

        if(!departmentString.isEmpty()){
            if(departmentService.get(departmentString)==null)
            {
                alertService.add(Alert.Type.danger,"Oddělení neexistuje.");
                response.sendRedirect("addNewEmployee.jsp");
            }
        }

        if(patternCheckService.doPatternCheck("[^0-9]",personalNumberString)){
            alertService.add(Alert.Type.danger,"Rodné číslo musí obsahovat jen čísla.");
            response.sendRedirect("addNewEmployee.jsp");
        }

        if(personalNumberString.equals(""))alertService.add(Alert.Type.danger,"RČ nesmí být prázdné");
        else if(personalNumberString.length()!=6)alertService.add(Alert.Type.danger,"RČ musí obsahovat 6 čísel");
        else if(personalService.get(personalNumberString)!=null)alertService.add(Alert.Type.danger,"RČ již je použito.");
        else if(patientService.get(personalNumberString)!=null&&!patientService.get(personalNumberString).getPacientPersonSurname().equals(surName)){
                alertService.add(Alert.Type.danger,"Již existuje pacient s tímto RČ ale jiným jménem."); }
        else if(firstName.length()>15)alertService.add(Alert.Type.danger,"Délka jména nesmí přesáhnout délku 15 znaků.");
        else if(surName.length()>20)alertService.add(Alert.Type.danger,"Délka příjmení nesmí přesáhnout délku 20 znaků.");
        else if(departmentString.length()>5)alertService.add(Alert.Type.danger,"Délka oddělení nesmí přesáhnout délku 5 znaků.");
        else if(surName.length()==0)alertService.add(Alert.Type.danger,"Příjmení nesmí být prázdné.");
        else if(patternCheckService.doPatternCheck("[^a-zA-Z]",surName))alertService.add(Alert.Type.danger,"Příjmení nesmí obsahovat speciální znaky a čísla.");
        else if(profession.equals(""))alertService.add(Alert.Type.danger,"Špatná profese, prosím zvolte jednu z (admin,Technicky personal,Zdravotnicky Personal).");
        else if(password.length()<8)alertService.add(Alert.Type.danger,"Heslo musí obsahovat alespoň 8 znaků.");
        else if(!profession.equals("admin")&&!profession.equals("Zdravotnicky Personal")&&!profession.equals("Technicky personal"))
            alertService.add(Alert.Type.danger, "Špatná profese, prosím zvolte jednu z (admin,Technicky personal,Zdravotnicky Personal).");
        else
            {
                int department=0;
                if (!departmentString.isEmpty()) department= Integer.parseInt(departmentString);
                int birthNumber = Integer.parseInt(personalNumberString);
                if (birthNumber>999999||birthNumber<0)alertService.add(Alert.Type.danger,"Rodné číslo musí být v rozsahu 0-9999999.");
                else if(department>999||department<0)alertService.add(Alert.Type.danger,"ID oddělení musí být v rozsahu 0-999.");
                else
                    {
                        try
                        {
                            personalService.create(birthNumber,firstName,surName,department,profession,password);
                        }
                        catch (Exception e)
                        {
                            throw new RuntimeException("Failed to store personal via Servlet.",e);
                        }
                        alertService.add(Alert.Type.success,"Zaměstnanec úspěšně přidán.");
                        response.sendRedirect("mainPage.jsp");
                    }
            }
        response.sendRedirect("addNewEmployee.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Neoprávněný přístup.");
     response.sendRedirect("index.jsp");
    }
}
