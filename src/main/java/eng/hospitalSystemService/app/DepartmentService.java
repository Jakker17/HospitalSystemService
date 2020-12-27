package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.HospitalRepository;
import eng.hospitalSystemService.db.entities.OddeleniEntity;

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
}
