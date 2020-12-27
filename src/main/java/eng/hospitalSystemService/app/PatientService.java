package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.PatientRepository;
import eng.hospitalSystemService.db.entities.PacientEntity;

public class PatientService {

    public static void create(int patientBirthNumber, String patientName, String patientSurname, String anamnesis, int medicaments, int personalBirthNumber, int roomID) {

        PatientRepository patientRepository = new PatientRepository();
        PacientEntity pacientEntity = new PacientEntity();
        pacientEntity.setPacientBirthnumber(patientBirthNumber);
        pacientEntity.setPacientPersonName(patientName);
        pacientEntity.setPacientPersonSurname(patientSurname);
        pacientEntity.setAnamnesis(anamnesis);
        pacientEntity.setMedicamentsid(medicaments);
        pacientEntity.setNursingStaffBirthnumber(personalBirthNumber);
        pacientEntity.setRoomid(roomID);

        patientRepository.insertPatient(pacientEntity);

    }
}
