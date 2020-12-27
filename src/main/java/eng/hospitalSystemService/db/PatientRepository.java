package eng.hospitalSystemService.db;

import eng.hospitalSystemService.db.entities.PacientEntity;
import eng.hospitalSystemService.db.entities.PersonalEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
