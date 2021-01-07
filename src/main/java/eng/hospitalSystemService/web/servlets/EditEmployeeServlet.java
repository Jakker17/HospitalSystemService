package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditEmployeeServlet",urlPatterns = "/editEmployee")
public class EditEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalService personalService = new PersonalService();
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();

        String personalNumberString = request.getParameter("birthNumber");
        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String departmentString = request.getParameter("department");
        String profession = request.getParameter("profession");
        String loginName = request.getParameter("loginName");

        if(patternCheckService.doPatternCheck("[^0-9]",departmentString))
        {
            alertService.add(Alert.Type.danger,"ID oddělení musí obsahovat pouze čísla.");
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }

        DepartmentService departmentService = new DepartmentService();
        if(departmentService.get(departmentString)==null)
        {
            alertService.add(Alert.Type.danger,"Oddělení neexistuje.");
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }

        if(personalService.get(personalNumberString)==null)
        {
            alertService.add(Alert.Type.danger,"Uživatel nenalezen.");
            response.sendRedirect("listOfEmployees.jsp");
        }
        else
        {
            int department=0;
            if (!departmentString.isEmpty()) department= Integer.parseInt(departmentString);
            if(patternCheckService.doPatternCheck("[^a-zA-Z]",surName))alertService.add(Alert.Type.danger,"Příjmení nesmí obsahovat speciální znaky ani čísla.");
            else if(surName.isEmpty())alertService.add(Alert.Type.danger,"Příjmení nesmí být prázdné.");
            else if(department>999||department<0)alertService.add(Alert.Type.danger,"ID oddělení musí být v rozsahu 0 - 999.");
            else
            {
                int birthNumber = Integer.parseInt(personalNumberString);
                try
                {
                    personalService.update(birthNumber,firstName,surName,department,loginName,profession);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Failed to store personal via Servlet.",e);
                }

                alertService.add(Alert.Type.success, "Uživatel upraven.");
                response.sendRedirect("listOfEmployees.jsp");
            }
            response.sendRedirect("editEmployee.jsp?birthNumber="+personalNumberString);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
