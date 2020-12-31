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

@WebServlet(name = "AddNewDepartmentServlet",urlPatterns = "/addNewDepartment")
public class AddNewDepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        DepartmentService departmentService= new DepartmentService();
        PatternCheckService patternCheckService = SessionServiceProvider.getPatternCheckService();

        String departmentIdString = request.getParameter("departmentNumber");
        String departmentName= request.getParameter("departmentName");
        int departmentID;

        if (departmentService.get(departmentIdString)!=null)
        {
            alertService.add(Alert.Type.danger,"Department number is already used.");
            response.sendRedirect("addNewDepartment.jsp");
        }

        if (patternCheckService.doPatternCheck("[^0-9]",departmentIdString))
        {
            alertService.add(Alert.Type.danger,"Department number must contain only numbers.");
            response.sendRedirect("addNewDepartment.jsp");
        }

        if (departmentIdString.equals(""))
        {
            alertService.add(Alert.Type.danger,"department number cannot be empty.");
            response.sendRedirect("addNewDepartment.jsp");
        }

        departmentID= Integer.parseInt(departmentIdString);

        if(departmentID>999||departmentID<0)alertService.add(Alert.Type.danger,"Department number must be between 0 and 1 000");
        else if(departmentIdString.length()>3)alertService.add(Alert.Type.danger,"Department number must be between 0 and 1 000");
        else if(departmentName.length()>120)alertService.add(Alert.Type.danger,"Department name Length cannot be bigger then 120 characters.");
        else if(departmentName.equals(""))alertService.add(Alert.Type.danger,"Department name cannot be empty.");
        else
            {
                departmentService.create(departmentID,departmentName);
                alertService.add(Alert.Type.success,"Department Added successfully.");
                response.sendRedirect("mainPage.jsp");
            }
        response.sendRedirect("addNewDepartment.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Unauthorized access.");
        response.sendRedirect("mainPage.jsp");
    }
}
