package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.AuthorizationService;
import eng.hospitalSystemService.app.SessionServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        AuthorizationService authorizationService = new AuthorizationService();

        if (!authorizationService.isLoggedUser(request))alertService.add(Alert.Type.danger,"There is no logged user to logout.");
        else
        {
            authorizationService.setLoggedUser(null, request);
            alertService.add(Alert.Type.success, "Successfully logged out.");
            response.sendRedirect("index.jsp");
        }
        response.sendRedirect("index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
