package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.PatientRepository;
import eng.hospitalSystemService.db.entities.PredpisEntity;

public class PrescriptionService {
    public void create(String nameOfMedicament, String usageOfMedicament, int birthNumberOfPatient) {
        PatientRepository patientRepository = new PatientRepository();

        PredpisEntity predpisEntity= new PredpisEntity();
        predpisEntity.setNameofmedicament(nameOfMedicament);
        predpisEntity.setUsageofmedicament(usageOfMedicament);
        predpisEntity.setBirtnumberofpatient(birthNumberOfPatient);
        patientRepository.insertPrescription(predpisEntity);
    }
}
