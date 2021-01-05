package eng.hospitalSystemService.db;

import eng.hospitalSystemService.db.entities.OddeleniEntity;
import eng.hospitalSystemService.db.entities.PersonalEntity;
import eng.hospitalSystemService.db.entities.PokojEntity;

import javax.persistence.*;
import java.util.List;

public class HospitalRepository {
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

    public void insertPersonal(PersonalEntity personalEntity) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(personalEntity);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            throw new DbException("Failed to insert item.",e);
        }


    }

    public void deletePersonal(int birthNumberOfPersonal) {
        EntityManager em = getEntityManager();
        try {

            PersonalEntity personalEntity = em.getReference(PersonalEntity.class, birthNumberOfPersonal);
            em.getTransaction().begin();
            em.remove(personalEntity);
            em.getTransaction().commit();

        }catch (Exception ex){

            throw new DbException("Failed to delete person by birthnumber"+ birthNumberOfPersonal,ex);
        }

    }

    public List<PersonalEntity> getAllPersonal() {
        List<PersonalEntity> ret;
        EntityManager em = getEntityManager();

        try {
            TypedQuery<PersonalEntity> q = em.createQuery("select i from PersonalEntity i order by i.personSurname asc",PersonalEntity.class);
             ret = q.getResultList();

        } catch (Exception e) {
            throw new DbException("failed to list Personal",e);
        }
        return ret;
    }

    public PersonalEntity getPersonal(int birthNumber) {
        EntityManager em = getEntityManager();
        PersonalEntity ret;
        try {
            ret = em.find(PersonalEntity.class,birthNumber);
        } catch (Exception e) {
            throw new DbException("Failed to get personal by birthNumber "+birthNumber,e);
        }
        return ret;
    }

    public void insertDepartment(OddeleniEntity oddeleniEntity){

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(oddeleniEntity);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            throw new DbException("Failed to insert item.",e);
        }
    }

    public OddeleniEntity getDepartment(int departmentID) {
        EntityManager em = getEntityManager();
        OddeleniEntity ret;
        try {
            ret = em.find(OddeleniEntity.class,departmentID);
        } catch (Exception e) {
            throw new DbException("Failed to get personal by birthNumber "+departmentID,e);
        }
        return ret;
    }

    public List<OddeleniEntity> getAllDepartments() {
        List<OddeleniEntity> ret;
        EntityManager em = getEntityManager();

        try {
            TypedQuery<OddeleniEntity> q = em.createQuery("select i from OddeleniEntity i order by i.idoddeleni asc",OddeleniEntity.class);
            ret = q.getResultList();
            ret.removeIf(oddeleniEntity -> oddeleniEntity.getIdoddeleni() == 0);

        } catch (Exception e) {
            throw new DbException("failed to list Departments.",e);
        }
        return ret;
    }

    public void deleteDepartment(int departmentID){
        EntityManager em = getEntityManager();
        try {

            OddeleniEntity oddeleniEntity = em.getReference(OddeleniEntity.class, departmentID);
            em.getTransaction().begin();
            em.remove(oddeleniEntity);
            em.getTransaction().commit();
        }
        catch (Exception ex){

            throw new DbException("Failed to delete Department by the ID"+ departmentID,ex);
        }
    }

    public void insertRoom(PokojEntity pokojEntity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pokojEntity);
            em.getTransaction().commit();
        }catch (Exception e)
        {
            throw new DbException("Unable to add room via repository",e);
        }
    }

    public PokojEntity getRoom(int roomID){
        EntityManager em = getEntityManager();
        PokojEntity ret;
        try {
            ret = em.find(PokojEntity.class,roomID);
        }catch (Exception e){throw new DbException("Failed to get room by ID "+roomID,e);}
        return ret;
    }

    public int getRoomCapacity(int roomID) {
        EntityManager em = getEntityManager();
        PokojEntity pokojEntity;
        try {
            pokojEntity = em.find(PokojEntity.class,roomID);
        }catch (Exception e){
            throw new DbException("Unable to find room Capacity",e);}
        return pokojEntity.getCapacity();
    }

    public void deleteRoom(int roomID) {
        EntityManager em = getEntityManager();

        try {
            PokojEntity pokojEntity = em.getReference(PokojEntity.class, roomID);
            em.getTransaction().begin();
            em.remove(pokojEntity);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            throw new DbException("Failed to delete room via repository",e);
        }
    }

    public List<PokojEntity> getAllRooms() {
        EntityManager em = getEntityManager();
        List<PokojEntity> ret;
        try {
            TypedQuery<PokojEntity> q = em.createQuery("select i from PokojEntity i order by i.roomid asc",PokojEntity.class);
            ret = q.getResultList();
            ret.removeIf(pokojEntity -> pokojEntity.getRoomid() == 0);
        }
        catch (Exception e)
        {
            throw new DbException("failed to list Rooms",e);
        }
        return ret;
    }

    public void updateRoom(PokojEntity pokojEntity) {

            EntityManager em = getEntityManager();

            try {
                em.getTransaction().begin();
                em.merge(pokojEntity);
                em.getTransaction().commit();

            }catch (Exception ex) {
                throw new DbException("Failed to update Room via Repository.", ex);
            }

    }

    public void updatePersonal(PersonalEntity personalEntity) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(personalEntity);
            em.getTransaction().commit();
        }catch (Exception ex) {
            throw new DbException("Failed to update Personal. ", ex);
        }

    }
}
