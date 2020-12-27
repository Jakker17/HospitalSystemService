package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditEmployeeServlet",urlPatterns = "/editEmployee")
public class EditEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String personalNumberString = request.getParameter("birthNumber");
        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String departmentString = request.getParameter("department");
        String profession = request.getParameter("profession");
        String loginName = request.getParameter("loginName");
        String passwordHashString = request.getParameter("password");
        String saltString = request.getParameter("salt");

        PersonalService personalService = new PersonalService();
        AlertService alertService = SessionServiceProvider.getAlertService(request);

        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(departmentString);
        boolean isStringContainsSpecialCharacter = matcher.find();

        if(isStringContainsSpecialCharacter)
        {
            alertService.add(Alert.Type.danger,"Department number must contain only numbers.");
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }

        DepartmentService departmentService = new DepartmentService();
        if(departmentService.get(departmentString)==null)
        {
            alertService.add(Alert.Type.danger,"Department doesn't exist.");
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }

        if(personalService.get(personalNumberString)==null)
        {
            alertService.add(Alert.Type.danger,"User not found or already deleted.");
            response.sendRedirect("listOfEmployees.jsp");
        }

        else

        {
            Pattern pattern1 = Pattern.compile("[^a-zA-Z]");
            matcher = pattern1.matcher(surName);
            isStringContainsSpecialCharacter = matcher.find();

            int department=0;
            if (!departmentString.isEmpty()) department= Integer.parseInt(departmentString);
            if(isStringContainsSpecialCharacter)alertService.add(Alert.Type.danger,"Surname cannot contains special characters or numbers.");
            else if(surName.isEmpty())alertService.add(Alert.Type.danger,"Surname cannot be empty.");
            else if(passwordHashString.length()<8)alertService.add(Alert.Type.danger,"Password has to have at least 8 characters.");
            else if(department>999||department<0)alertService.add(Alert.Type.danger,"Department number must be between 0 and 1 000");
            else
            {
                int birthNumber = Integer.parseInt(personalNumberString);
                byte[] passwordHash = passwordHashString.getBytes();
                byte[] saltHash = saltString.getBytes();

                try
                {
                    personalService.update(birthNumber,firstName,surName,department,loginName,profession,passwordHash,saltHash);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Failed to store personal via Servlet.",e);
                }

                alertService.add(Alert.Type.success, "User has been edited.");
                response.sendRedirect("index.jsp");
            }
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
