package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.HospitalRepository;
import eng.hospitalSystemService.db.entities.OddeleniEntity;
import eng.hospitalSystemService.db.entities.PersonalEntity;
import eng.hospitalSystemService.db.entities.PokojEntity;

import java.util.List;

public class DepartmentService {
    public void create(int departmentID, String departmentName) {

        OddeleniEntity oddeleniEntity = new OddeleniEntity();
        oddeleniEntity.setIdoddeleni(departmentID);
        oddeleniEntity.setNazevoddeleni(departmentName);
        HospitalRepository hospitalRepository = new HospitalRepository();
        hospitalRepository.insertDepartment(oddeleniEntity);
    }

    public OddeleniEntity get(int departmentID){
        return new HospitalRepository().getDepartment(departmentID);
    }

    public OddeleniEntity get(String departmentID){
        return this.get(Integer.parseInt(departmentID));
    }

    public List<OddeleniEntity> getListOfDepartments(){
        HospitalRepository hospitalRepository = new HospitalRepository();
        List<OddeleniEntity> listOfDepartments= hospitalRepository.getAllDepartments();
        return  listOfDepartments;
    }

    public void delete(int departmentID)
    {
     HospitalRepository hospitalRepository = new HospitalRepository();
     hospitalRepository.deleteDepartment(departmentID);
    }

    public boolean isDepartmentEmpty(int departmentID) {
        PersonalService personalService = new PersonalService();
        RoomService roomService = new RoomService();
        List<PersonalEntity> personal = personalService.getListOfPersonalByDepartment(departmentID);
        List<PokojEntity> rooms = roomService.getAllRoomsByDepartment(departmentID);
        if (personal.isEmpty()){
            if (rooms.isEmpty()) return true;
        }
        return false;
    }
}
