package eng.hospitalSystemService.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "POKOJ", schema = "ADMIN", catalog = "")
public class PokojEntity {
    private int roomid;
    private int capacity;
    private int departmentid;

    @Id
    @Column(name = "ROOMID", nullable = false)
    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    @Basic
    @Column(name = "CAPACITY", nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "DEPARTMENTID", nullable = false)
    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokojEntity that = (PokojEntity) o;

        if (roomid != that.roomid) return false;
        if (capacity != that.capacity) return false;
        if (departmentid != that.departmentid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomid;
        result = 31 * result + capacity;
        result = 31 * result + departmentid;
        return result;
    }
}
