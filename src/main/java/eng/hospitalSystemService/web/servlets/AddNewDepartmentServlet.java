package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            alertService.add(Alert.Type.danger,"ID oddělení již je použito.");
            response.sendRedirect("addNewDepartment.jsp");
        }

        if (patternCheckService.doPatternCheck("[^0-9]",departmentIdString))
        {
            alertService.add(Alert.Type.danger,"ID oddělení musí obsahovat jen čísla.");
            response.sendRedirect("addNewDepartment.jsp");
        }

        if (departmentIdString.equals(""))
        {
            alertService.add(Alert.Type.danger,"ID oddělení nesmí být prázdné");
            response.sendRedirect("addNewDepartment.jsp");
        }

        departmentID= Integer.parseInt(departmentIdString);

        if(departmentID>999||departmentID<0)alertService.add(Alert.Type.danger,"ID oddělení musí být v rozsahu 0-999");
        else if(departmentIdString.length()>5)alertService.add(Alert.Type.danger,"ID oddělení nesmí obsahovat více jak 5 znaků.");
        else if(departmentName.length()>120)alertService.add(Alert.Type.danger,"Název oddělení nesmí být větší než 120 znaků.");
        else if(departmentName.equals(""))alertService.add(Alert.Type.danger,"Název oddělení nesmí být prázdný.");
        else
            {
                departmentService.create(departmentID,departmentName);
                alertService.add(Alert.Type.success,"Oddělení přidáno úspěšně.");
                response.sendRedirect("mainPage.jsp");
            }
        response.sendRedirect("addNewDepartment.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
