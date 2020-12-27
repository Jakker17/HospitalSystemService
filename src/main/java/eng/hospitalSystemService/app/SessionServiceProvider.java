package eng.hospitalSystemService.app;

import javax.servlet.http.HttpServletRequest;

public class SessionServiceProvider {

    public static AlertService getAlertService(HttpServletRequest request) {AlertService alertService = (AlertService) request.getSession().getAttribute("alertService");
        return alertService;
    }
}
