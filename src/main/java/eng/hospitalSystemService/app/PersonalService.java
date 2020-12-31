package eng.hospitalSystemService.app;

import eng.hospitalSystemService.db.HospitalRepository;
import eng.hospitalSystemService.db.entities.PersonalEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

public class PersonalService {
    public void create(int birthNumber, String firstName, String surName, int department, String profession, String password) {
        byte[] salt;
        byte[] passwordHash;

        salt = SecurityUtils.generateSalt();
        passwordHash = SecurityUtils.hashPassword(password,salt);

        Random random = new Random();

        PersonalEntity personalEntity = new PersonalEntity();
        personalEntity.setPersonName(firstName);
        personalEntity.setBirthnumber(birthNumber);
        personalEntity.setPersonSurname(surName);
        personalEntity.setDepartment(department);
        personalEntity.setProffesion(profession);
        personalEntity.setLoginName(surName+random.nextInt(100));
        personalEntity.setPasswordHash(passwordHash);
        personalEntity.setSaltHash(salt);

        HospitalRepository hospitalRepository = new HospitalRepository();
        hospitalRepository.insertPersonal(personalEntity);
    }

    public void update(int birthNumber, String firstName, String surName, int department, String loginName, String profession, byte[] passwordHash, byte[] saltHash) {
        PersonalEntity personalEntity= new PersonalEntity();

        personalEntity.setBirthnumber(birthNumber);
        personalEntity.setPersonSurname(surName);
        personalEntity.setPersonName(firstName);
        personalEntity.setDepartment(department);
        personalEntity.setProffesion(profession);
        personalEntity.setLoginName(loginName);
        personalEntity.setSaltHash(saltHash);
        personalEntity.setPasswordHash(passwordHash);

        HospitalRepository hospitalRepository = new HospitalRepository();
        hospitalRepository.updatePersonal(personalEntity);
    }

    public void delete(int birthNumberOfPersonal) {
        HospitalRepository hospitalRepository = new HospitalRepository();
        hospitalRepository.deletePersonal(birthNumberOfPersonal);
    }

    public List<PersonalEntity> getListOfPersonal(){
        HospitalRepository hospitalRepository = new HospitalRepository();
        List<PersonalEntity> listOfPersonal= hospitalRepository.getAllPersonal();
        return listOfPersonal;
    }

    public PersonalEntity get(int birthNumber){
        return new HospitalRepository().getPersonal(birthNumber);
}

    public PersonalEntity get(String birthNumber){
        return this.get(Integer.parseInt(birthNumber));
}


    public int getPersonalBirthNumberBySurname(String personalSurname) {
        HospitalRepository hospitalRepository= new HospitalRepository();
        List<PersonalEntity> personals = hospitalRepository.getAllPersonal();
        for (PersonalEntity personalEntity:personals) {
            if(personalEntity.getPersonSurname().equals(personalSurname)) return personalEntity.getBirthnumber();

        }
        return -1;
    }

    public PersonalEntity getPersonalByLogin(String loginInput) {
        HospitalRepository hospitalRepository = new HospitalRepository();
        List<PersonalEntity> personalList = hospitalRepository.getAllPersonal();

        for (PersonalEntity personalEntity: personalList) {
            if (personalEntity.getLoginName().equals(loginInput))return  personalEntity;
        }
        return null;
    }
}
