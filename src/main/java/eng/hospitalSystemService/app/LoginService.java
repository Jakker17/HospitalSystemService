package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.entities.PersonalEntity;

import java.sql.SQLException;
import java.util.Arrays;

public class LoginService {

    public boolean checkPassword(String loginInput, String passwordInput) throws SQLException {
        PersonalService personalService = new PersonalService();
        PersonalEntity personalEntity;
        personalEntity = personalService.getPersonalByLogin(loginInput);
        GetBlobService getBlobService = new GetBlobService();

        if (personalEntity==null) return false;

        byte[] salt = getBlobService.getPasswordBlobFromDatabase("salt_hash",personalEntity.getBirthnumber());
        byte[] password= getBlobService.getPasswordBlobFromDatabase("password_hash",personalEntity.getBirthnumber());

        byte[] passwordIn = SecurityUtils.hashPassword(passwordInput,salt);

        return Arrays.equals(password, passwordIn);
    }
}
