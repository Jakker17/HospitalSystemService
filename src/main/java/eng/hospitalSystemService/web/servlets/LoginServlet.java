package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.*;
import eng.hospitalSystemService.db.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        AuthorizationService authorizationService = new AuthorizationService();
        PersonalService personalService= new PersonalService();

        String loginInput = request.getParameter("loginName");
        String passwordInput = request.getParameter("password");

        LoginService loginService = new LoginService();

        if (loginInput.equals(""))alertService.add(Alert.Type.danger,"Login nesmí být prázdný.");
        else if (passwordInput.equals(""))alertService.add(Alert.Type.danger,"Heslo nesmí být prázdné.");
        else {
            try {
                if (loginService.checkPassword(loginInput,passwordInput))
                {
                    if (authorizationService.isLoggedUser(request)){alertService.add(Alert.Type.danger,"Uživatel již je přihlášen.");
                    response.sendRedirect("mainPage.jsp");}
                    authorizationService.setLoggedUser(personalService.getPersonalByLogin(loginInput),request);
                    alertService.add(Alert.Type.success,"Úspěšně přihlášen.");
                    response.sendRedirect("mainPage.jsp");
                }
                else
                    {
                        alertService.add(Alert.Type.danger,"Špatný login/heslo, zkuste znovu.");
                        response.sendRedirect("index.jsp");
                    }
            } catch (SQLException throwables) {
                throw new DbException("problem with password from db. ",throwables);
            }
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Neoprávněný přístup.");
        response.sendRedirect("index.jsp");
    }
}
