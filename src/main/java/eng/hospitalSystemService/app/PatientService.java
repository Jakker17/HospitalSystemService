package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.PatientRepository;
import eng.hospitalSystemService.db.entities.PacientEntity;

import java.util.List;

public class PatientService {

    public  void create(int patientBirthNumber, String patientName, String patientSurname, String anamnesis, int personalBirthNumber, int roomID) {

        PatientRepository patientRepository = new PatientRepository();
        PacientEntity pacientEntity = new PacientEntity();
        pacientEntity.setPacientBirthnumber(patientBirthNumber);
        pacientEntity.setPacientPersonName(patientName);
        pacientEntity.setPacientPersonSurname(patientSurname);
        pacientEntity.setAnamnesis(anamnesis);
        pacientEntity.setNursingStaffBirthnumber(personalBirthNumber);
        pacientEntity.setRoomid(roomID);

        patientRepository.insertPatient(pacientEntity);

    }

    public PacientEntity get(int patientBirthNumberString) {
        return new PatientRepository().getPatient(patientBirthNumberString);
    }

    public PacientEntity get(String patientBirthNumberString) {
        return this.get(Integer.parseInt(patientBirthNumberString));
    }

    public List<PacientEntity> getListOfPatient(){
        PatientRepository patientRepository = new PatientRepository();
        return patientRepository.getAll();
    }

    public void delete(int patientBirthNumber) {
        PatientRepository patientRepository= new PatientRepository();
        patientRepository.delete(patientBirthNumber);
    }

    public boolean isPatientAssignedToThisStaff(int birthNumberOfPersonal) {
        PatientRepository patientRepository = new PatientRepository();
        List<PacientEntity> patients = patientRepository.getAll();
        for (PacientEntity pacientEntity: patients) {
            if (pacientEntity.getNursingStaffBirthnumber()==birthNumberOfPersonal)return true;
        }
        return false;
    }

    public void update(String patientName, String patientSurname, int patientBirthNumber, String patientAnamnesis, int patientRoomId, int patientNursingStaffBirthNumber) {
        PacientEntity pacientEntity = new PacientEntity();
        pacientEntity.setPacientPersonName(patientName);
        pacientEntity.setPacientPersonSurname(patientSurname);
        pacientEntity.setPacientBirthnumber(patientBirthNumber);
        pacientEntity.setAnamnesis(patientAnamnesis);
        pacientEntity.setRoomid(patientRoomId);
        pacientEntity.setNursingStaffBirthnumber(patientNursingStaffBirthNumber);

        PatientRepository patientRepository= new PatientRepository();
        patientRepository.update(pacientEntity);
    }

    public List<PacientEntity> getAllBYNursingStaff(int nursingStaffBirthNumber) {
        PatientRepository patientRepository = new PatientRepository();
        List<PacientEntity> patients = patientRepository.getAll();
        patients.removeIf(pacientEntity -> !pacientEntity.getNursingStaffBirthnumber().equals(nursingStaffBirthNumber) );
        return patients;
    }

    public List<PacientEntity> getPatientsAtDepartment(int departmentID){
        PatientRepository patientRepository= new PatientRepository();
        List<PacientEntity> patients = patientRepository.getAll();
        RoomService roomService = new RoomService();

        patients.removeIf(patient -> roomService.getRoom(patient.getRoomid()).getDepartmentid() != departmentID);
        return patients;
    }

    public List<PacientEntity> getAllByRoom(int roomID){
        PatientRepository patientRepository = new PatientRepository();
        List<PacientEntity> patients = patientRepository.getAll();
        patients.removeIf(pacientEntity -> !pacientEntity.getRoomid().equals(roomID));
        return  patients;
    }
}

