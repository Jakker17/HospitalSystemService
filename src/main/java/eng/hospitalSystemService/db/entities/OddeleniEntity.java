package eng.hospitalSystemService.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "ODDELENI", schema = "ADMIN", catalog = "")
public class OddeleniEntity {
    private int idoddeleni;
    private String nazevoddeleni;

    @Id
    @Column(name = "IDODDELENI")
    public int getIdoddeleni() {
        return idoddeleni;
    }

    public void setIdoddeleni(int idoddeleni) {
        this.idoddeleni = idoddeleni;
    }

    @Basic
    @Column(name = "NAZEVODDELENI")
    public String getNazevoddeleni() {
        return nazevoddeleni;
    }

    public void setNazevoddeleni(String nazevoddeleni) {
        this.nazevoddeleni = nazevoddeleni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OddeleniEntity that = (OddeleniEntity) o;

        if (idoddeleni != that.idoddeleni) return false;
        if (nazevoddeleni != null ? !nazevoddeleni.equals(that.nazevoddeleni) : that.nazevoddeleni != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoddeleni;
        result = 31 * result + (nazevoddeleni != null ? nazevoddeleni.hashCode() : 0);
        return result;
    }
}
