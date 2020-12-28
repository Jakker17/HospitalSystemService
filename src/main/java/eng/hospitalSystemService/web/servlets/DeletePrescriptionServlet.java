package eng.hospitalSystemService.web.servlets;

import eng.hospitalSystemService.app.Alert;
import eng.hospitalSystemService.app.AlertService;
import eng.hospitalSystemService.app.PrescriptionService;
import eng.hospitalSystemService.app.SessionServiceProvider;
import org.eclipse.persistence.exceptions.i18n.ValidationExceptionResource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePrescriptionServlet",urlPatterns = "/deletePrescription")
public class DeletePrescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        PrescriptionService prescriptionService = new PrescriptionService();

        String prescriptionIdString = request.getParameter("prescriptionID");
        int prescriptionId = Integer.parseInt(prescriptionIdString);

        prescriptionService.delete(prescriptionId);
        alertService.add(Alert.Type.success,"successfully deleted Prescription");
        response.sendRedirect("");//not implemented yet

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlertService alertService = SessionServiceProvider.getAlertService(request);
        alertService.add(Alert.Type.danger,"Unauthorized access.");
        response.sendRedirect("index.jsp");
    }
}
