package eng.hospitalSystemService.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "PACIENT", schema = "ADMIN", catalog = "")
public class PacientEntity {
    private int pacientBirthnumber;
    private String pacientPersonName;
    private String pacientPersonSurname;
    private String anamnesis;
    private Integer roomid;
    private Integer nursingStaffBirthnumber;

    @Id
    @Column(name = "PACIENT_BIRTHNUMBER", nullable = false)
    public int getPacientBirthnumber() {
        return pacientBirthnumber;
    }

    public void setPacientBirthnumber(int pacientBirthnumber) {
        this.pacientBirthnumber = pacientBirthnumber;
    }

    @Basic
    @Column(name = "PACIENT_PERSON_NAME", nullable = true, length = 60)
    public String getPacientPersonName() {
        return pacientPersonName;
    }

    public void setPacientPersonName(String pacientPersonName) {
        this.pacientPersonName = pacientPersonName;
    }

    @Basic
    @Column(name = "PACIENT_PERSON_SURNAME", nullable = false, length = 60)
    public String getPacientPersonSurname() {
        return pacientPersonSurname;
    }

    public void setPacientPersonSurname(String pacientPersonSurname) {
        this.pacientPersonSurname = pacientPersonSurname;
    }

    @Basic
    @Column(name = "ANAMNESIS", nullable = false, length = 255)
    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }


    @Basic
    @Column(name = "ROOMID", nullable = true)
    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacientEntity that = (PacientEntity) o;

        if (pacientBirthnumber != that.pacientBirthnumber) return false;
        if (pacientPersonName != null ? !pacientPersonName.equals(that.pacientPersonName) : that.pacientPersonName != null)
            return false;
        if (pacientPersonSurname != null ? !pacientPersonSurname.equals(that.pacientPersonSurname) : that.pacientPersonSurname != null)
            return false;
        if (anamnesis != null ? !anamnesis.equals(that.anamnesis) : that.anamnesis != null) return false;
        if (roomid != null ? !roomid.equals(that.roomid) : that.roomid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pacientBirthnumber;
        result = 31 * result + (pacientPersonName != null ? pacientPersonName.hashCode() : 0);
        result = 31 * result + (pacientPersonSurname != null ? pacientPersonSurname.hashCode() : 0);
        result = 31 * result + (anamnesis != null ? anamnesis.hashCode() : 0);
        result = 31 * result + (roomid != null ? roomid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "NURSING_STAFF_BIRTHNUMBER", nullable = true)
    public Integer getNursingStaffBirthnumber() {
        return nursingStaffBirthnumber;
    }

    public void setNursingStaffBirthnumber(Integer nursingStaffBirthnumber) {
        this.nursingStaffBirthnumber = nursingStaffBirthnumber;
    }
}
