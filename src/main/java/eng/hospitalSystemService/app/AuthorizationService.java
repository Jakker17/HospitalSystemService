package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.entities.PersonalEntity;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationService {
    private final static String LOGGED_USER_KEY = "__loggedUser";

    public void setLoggedUser(PersonalEntity personalEntity, HttpServletRequest request){
        request.getSession().setAttribute(LOGGED_USER_KEY,personalEntity);
    }

    public PersonalEntity getLoggedUser(HttpServletRequest request){
        return (PersonalEntity) request.getSession().getAttribute(LOGGED_USER_KEY);
    }

    public boolean isLoggedAdmin(HttpServletRequest request){
        PersonalEntity personalEntity = getLoggedUser(request);
        return personalEntity.getProffesion().equals("admin");
    }

    public boolean isLoggedMedicalStaff(HttpServletRequest request){
        PersonalEntity personalEntity = getLoggedUser(request);
        return personalEntity.getProffesion().equals("Zdravotnicky Personal");
    }

    public boolean isLoggedUser(HttpServletRequest request){
        return getLoggedUser(request)!=null;
    }

}
