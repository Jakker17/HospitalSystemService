package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteDepartmentServlet",urlPatterns = "/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);

        String departmentIDString = request.getParameter("departmentID");
        int departmentID = Integer.parseInt(departmentIDString);

        DepartmentService departmentService = new DepartmentService();
        if(departmentService.get(departmentID)==null)
        {
            alertService.add(Alert.Type.danger,"Oddělení nenalezeno.");
            response.sendRedirect("listOfDepartments.jsp");
        }
        else {
            if (departmentService.isDepartmentEmpty(departmentID))
            {
                try {
                    departmentService.delete(departmentID);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to delete department via Servlet.", e);
                }
                alertService.add(Alert.Type.success,"Oddělení smazáno");
                response.sendRedirect("listOfDepartments.jsp");
            }
            else{alertService.add(Alert.Type.danger,"Oddělení není prázdné. Přesuňte pokoje a personál nejdříve.");}
        }
        response.sendRedirect("departmentInventory.jsp?departmentID="+departmentID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger, "Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
