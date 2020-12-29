package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.PatientRepository;
import eng.hospitalSystemService.db.entities.PredpisEntity;

import java.util.List;

public class PrescriptionService {
    public void create(String nameOfMedicament, String usageOfMedicament, int birthNumberOfPatient) {
        PatientRepository patientRepository = new PatientRepository();

        PredpisEntity predpisEntity= new PredpisEntity();
        predpisEntity.setNameofmedicament(nameOfMedicament);
        predpisEntity.setUsageofmedicament(usageOfMedicament);
        predpisEntity.setBirtnumberofpatient(birthNumberOfPatient);
        patientRepository.insertPrescription(predpisEntity);
    }

    public void delete(int prescriptionId) {
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.deletePrescription(prescriptionId);
    }

    public void deleteByPatient(int patientBirthNumber){
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.deleteAllPrescriptionsByPatient(patientBirthNumber);
    }

    public List<PredpisEntity> getAllByPatient(int patientBirthNumber){
        return new PatientRepository().getAllPrescriptionByPatient(patientBirthNumber);
    }
}
