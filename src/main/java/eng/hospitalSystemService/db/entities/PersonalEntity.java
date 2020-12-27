package eng.hospitalSystemService.db.entities;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "PERSONAL", schema = "ADMIN", catalog = "")
public class PersonalEntity {
    private int birthnumber;
    private String personName;
    private String personSurname;
    private String proffesion;
    private Integer department;
    private String loginName;
    private byte[] passwordHash;
    private byte[] saltHash;

    @Id
    @Column(name = "BIRTHNUMBER")
    public int getBirthnumber() {
        return birthnumber;
    }

    public void setBirthnumber(int birthnumber) {
        this.birthnumber = birthnumber;
    }

    @Basic
    @Column(name = "PERSON_NAME")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Basic
    @Column(name = "PERSON_SURNAME")
    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    @Basic
    @Column(name = "PROFFESION")
    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    @Basic
    @Column(name = "DEPARTMENT")
    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Basic
    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "PASSWORD_HASH")
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "SALT_HASH")
    public byte[] getSaltHash() {
        return saltHash;
    }

    public void setSaltHash(byte[] saltHash) {
        this.saltHash = saltHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalEntity that = (PersonalEntity) o;

        if (birthnumber != that.birthnumber) return false;
        if (personName != null ? !personName.equals(that.personName) : that.personName != null) return false;
        if (personSurname != null ? !personSurname.equals(that.personSurname) : that.personSurname != null)
            return false;
        if (proffesion != null ? !proffesion.equals(that.proffesion) : that.proffesion != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;
        if (!Arrays.equals(passwordHash, that.passwordHash)) return false;
        if (!Arrays.equals(saltHash, that.saltHash)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = birthnumber;
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (personSurname != null ? personSurname.hashCode() : 0);
        result = 31 * result + (proffesion != null ? proffesion.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(passwordHash);
        result = 31 * result + Arrays.hashCode(saltHash);
        return result;
    }
}
