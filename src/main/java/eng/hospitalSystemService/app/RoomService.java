package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.HospitalRepository;
import eng.hospitalSystemService.db.PatientRepository;
import eng.hospitalSystemService.db.entities.PokojEntity;

import java.util.List;

public class RoomService {
    public void create(int roomId, int roomCapacity, int departmentId) {
        HospitalRepository hospitalRepository = new HospitalRepository();
        PokojEntity pokojEntity = new PokojEntity();
        pokojEntity.setRoomid(roomId);
        pokojEntity.setCapacity(roomCapacity);
        pokojEntity.setDepartmentid(departmentId);

        hospitalRepository.insertRoom(pokojEntity);
    }
    public PokojEntity getRoom(int roomID){
        return new HospitalRepository().getRoom(roomID);
    }

    public PokojEntity getRoom(String roomIDString){
      return this.getRoom(Integer.parseInt(roomIDString));
    }

    public boolean checkAvailiabilityOfRoom(int roomID){
        PatientRepository patientRepository = new PatientRepository();
        int numberOfPatient = patientRepository.getNumberOfPatientsAtRoom(roomID);
        HospitalRepository hospitalRepository = new HospitalRepository();

        if (numberOfPatient >= hospitalRepository.getRoomCapacity(roomID))
        {
            return false;
        }
        else
            {
        return true;
            }
    }

    public void delete(int roomID) {
        HospitalRepository hospitalRepository = new HospitalRepository();
        hospitalRepository.deleteRoom(roomID);
    }

    public List<PokojEntity> getAllRooms(){
        return new HospitalRepository().getAllRooms();
    }

    public boolean isRoomEmpty(int roomID) {
        PatientRepository patientRepository = new PatientRepository();
        if(patientRepository.getAllFromRoom(roomID)==0) {return true;}
        else {
        return false;}
    }
}
