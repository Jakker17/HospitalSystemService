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

@WebServlet(name = "AddNewEmployeeServlet",urlPatterns = "/addNewEmployee")
public class AddNewEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PersonalService personalService = new PersonalService();
        DepartmentService departmentService = new DepartmentService();
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();

        String personalNumberString = request.getParameter("birthNumber");
        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String departmentString = request.getParameter("department");
        String profession = request.getParameter("profession");
        String password = request.getParameter("password");

        if(!departmentString.isEmpty()){
            if(departmentService.get(departmentString)==null)
            {
                alertService.add(Alert.Type.danger,"Department is not existing");
                response.sendRedirect("addNewEmployee.jsp");
            }
        }

        if(patternCheckService.doPatternCheck("[^0-9]",departmentString)){
            alertService.add(Alert.Type.danger,"Department number must contain only numbers.");
            response.sendRedirect("addNewEmployee.jsp");
        }

        if(patternCheckService.doPatternCheck("[^0-9]",personalNumberString)){
            alertService.add(Alert.Type.danger,"Birth number must contain only numbers.");
            response.sendRedirect("addNewEmployee.jsp");
        }

        if(personalService.get(personalNumberString)!=null)alertService.add(Alert.Type.danger,"Birth number already added.");
        else if(personalNumberString.equals(""))alertService.add(Alert.Type.danger,"Birth number cannot empty");
        else if(personalNumberString.length()!=6)alertService.add(Alert.Type.danger,"Birth number can have only 6 characters");
        else if(surName.length()==0)alertService.add(Alert.Type.danger,"Surname cannot be empty.");
        else if(patternCheckService.doPatternCheck("[^a-zA-Z]",surName))alertService.add(Alert.Type.danger,"Surname cannot contains special characters or numbers.");
        else if(profession.equals(""))alertService.add(Alert.Type.danger,"Invalid profession,Choose one from (admin,Technicky personal,Zdravotnicky Personal)");
        else if(password.length()<8)alertService.add(Alert.Type.danger,"Password has to have at least 8 characters.");
        else if(!profession.equals("admin")&&!profession.equals("Zdravotnicky Personal")&&!profession.equals("Technicky personal"))
            alertService.add(Alert.Type.danger, "Invalid profession, choose one from (admin,Technicky personal,Zdravotnicky Personal)");
        else
            {
                int department=0;
                if (!departmentString.isEmpty()) department= Integer.parseInt(departmentString);
                int birthNumber = Integer.parseInt(personalNumberString);
                if (birthNumber>999999||birthNumber<0)alertService.add(Alert.Type.danger,"Birth number must be between 0 and 1 000 000");
                else if(department>999||department<0)alertService.add(Alert.Type.danger,"Department number must be between 0 and 1 000");
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
                        alertService.add(Alert.Type.success,"User has been added.");
                        response.sendRedirect("index.jsp");
                    }

            }
        response.sendRedirect("addNewEmployee.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Unauthorized access.");
     response.sendRedirect("index.jsp");
    }
}
