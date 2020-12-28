package eng.hospitalSystemService.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "PREDPIS", schema = "ADMIN", catalog = "")
public class PredpisEntity {
    private int prescriptionid;
    private String nameofmedicament;
    private String usageofmedicament;
    private Integer birtnumberofpatient;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRESCRIPTIONID")
    public int getPrescriptionid() {
        return prescriptionid;
    }

    public void setPrescriptionid(int prescriptionid) {
        this.prescriptionid = prescriptionid;
    }

    @Basic
    @Column(name = "NAMEOFMEDICAMENT")
    public String getNameofmedicament() {
        return nameofmedicament;
    }

    public void setNameofmedicament(String nameofmedicament) {
        this.nameofmedicament = nameofmedicament;
    }

    @Basic
    @Column(name = "USAGEOFMEDICAMENT")
    public String getUsageofmedicament() {
        return usageofmedicament;
    }

    public void setUsageofmedicament(String usageofmedicament) {
        this.usageofmedicament = usageofmedicament;
    }

    @Basic
    @Column(name = "BIRTNUMBEROFPATIENT")
    public Integer getBirtnumberofpatient() {
        return birtnumberofpatient;
    }

    public void setBirtnumberofpatient(Integer birtnumberofpatient) {
        this.birtnumberofpatient = birtnumberofpatient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredpisEntity that = (PredpisEntity) o;

        if (prescriptionid != that.prescriptionid) return false;
        if (nameofmedicament != null ? !nameofmedicament.equals(that.nameofmedicament) : that.nameofmedicament != null)
            return false;
        if (usageofmedicament != null ? !usageofmedicament.equals(that.usageofmedicament) : that.usageofmedicament != null)
            return false;
        if (birtnumberofpatient != null ? !birtnumberofpatient.equals(that.birtnumberofpatient) : that.birtnumberofpatient != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prescriptionid;
        result = 31 * result + (nameofmedicament != null ? nameofmedicament.hashCode() : 0);
        result = 31 * result + (usageofmedicament != null ? usageofmedicament.hashCode() : 0);
        result = 31 * result + (birtnumberofpatient != null ? birtnumberofpatient.hashCode() : 0);
        return result;
    }
}
