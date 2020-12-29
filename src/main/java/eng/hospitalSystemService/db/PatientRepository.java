package eng.hospitalSystemService.db;

import eng.hospitalSystemService.db.entities.PacientEntity;
import eng.hospitalSystemService.db.entities.PredpisEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientRepository {
    private static final String PU_NAME = "PU_HOSPITAL";
    private static EntityManagerFactory emf = null;

    private EntityManager getEntityManager(){
        if(emf==null)
            try {
                emf = Persistence.createEntityManagerFactory(PU_NAME);
            }catch (Exception e){
                throw new DbException("Failed to create entity-manager-factory.",e);
            }

        EntityManager em;
        try {
            em = emf.createEntityManager();
        }catch (Exception e){

            throw new DbException("Failed to create entity-manager.",e);
        }
        return em;
    }

    public void insertPatient(PacientEntity pacientEntity) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(pacientEntity);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            throw new DbException("Failed to insert item Pacient.",e);
        }
    }

    public PacientEntity getPatient(int birthNumber){

        EntityManager em = getEntityManager();
        PacientEntity ret;
        try {
            ret = em.find(PacientEntity.class,birthNumber);
        } catch (Exception e) {
            throw new DbException("Failed to get patient by birthNumber "+birthNumber,e);
        }
        return ret;
    }

    public List<PacientEntity> getAll() {
        List<PacientEntity> ret;
        EntityManager em = getEntityManager();

        try {
            TypedQuery<PacientEntity> q = em.createQuery("select i from PacientEntity i order by i.pacientPersonSurname asc",PacientEntity.class);
            ret = q.getResultList();

        } catch (Exception e) {
            throw new DbException("failed to list Pacients",e);
        }
        return ret;
    }

    public void delete(int patientBirthNumber) {
        EntityManager em = getEntityManager();
        try {

            PacientEntity pacientEntity = em.getReference(PacientEntity.class, patientBirthNumber);
            em.getTransaction().begin();
            em.remove(pacientEntity);
            em.getTransaction().commit();

        }catch (Exception ex){

            throw new DbException("Failed to delete patient by birthnumber"+ patientBirthNumber,ex);
        }

    }

    public void update(PacientEntity pacientEntity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pacientEntity);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DbException("Failed to update Patient.",ex);
        }
    }

    public void insertPrescription(PredpisEntity predpisEntity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(predpisEntity);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DbException("Unable to insert Prescrtiption via Repository.",ex);
        }
    }

    public void deletePrescription(int prescriptionId) {
        EntityManager em = getEntityManager();
        try {
            PredpisEntity predpisEntity = em.getReference(PredpisEntity.class,prescriptionId);
            em.getTransaction().begin();
            em.remove(predpisEntity);
            em.getTransaction().commit();
        }catch (Exception e){
            throw new DbException("Unable to delete Prescrtiption via Repository",e);
        }
    }

    public List<PredpisEntity> getAllPrescriptionByPatient(int patientBirthNumber) {
        EntityManager em = getEntityManager();
        List<PredpisEntity> ret ;

        try {
       TypedQuery<PredpisEntity>q = em.createQuery("select i from PredpisEntity i order by i.prescriptionid asc",PredpisEntity.class);
               ret = q.getResultList();

               ret.removeIf(predpisEntity -> !predpisEntity.getBirtnumberofpatient().equals(patientBirthNumber));

        }catch (Exception e){
            throw new DbException("Failed to list all prescriptions by Patient Birth number.",e);
        }
        return ret;
    }

    public void deleteAllPrescriptionsByPatient(int patientBirthNumber) {
        EntityManager em = getEntityManager();
        List<PredpisEntity> prescriptions;
        try {
            TypedQuery<PredpisEntity> q = em.createQuery("select i from PredpisEntity i order by i.prescriptionid asc",PredpisEntity.class);
            prescriptions = q.getResultList();
            for (PredpisEntity predpisEntity: prescriptions) {
                if (predpisEntity.getBirtnumberofpatient().equals(patientBirthNumber))
                {
                    em.getTransaction().begin();
                    em.remove(predpisEntity);
                    em.getTransaction().commit();
                }
            }
        }
        catch (Exception e)
        {
            throw new DbException("Unable to delete all prescriptions by patientNumber via repository",e);
        }


    }
}
