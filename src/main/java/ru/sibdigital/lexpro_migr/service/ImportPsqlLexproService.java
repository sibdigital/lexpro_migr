package ru.sibdigital.lexpro_migr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.OrgEntity;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;
import ru.sibdigital.lexpro_migr.model.zakon.SpDoljnostEntity;
import ru.sibdigital.lexpro_migr.model.zakon.UsersEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.*;
import ru.sibdigital.lexpro_migr.repo.zakon.PersonRepo;
import ru.sibdigital.lexpro_migr.utils.StrUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportPsqlLexproService {

    @Autowired
    @Qualifier("psqlLexproEntityManager")
    EntityManager psqlLexproEntityManager;

    @Autowired
    ClsOrganizationTypeRepo clsOrganizationTypeRepo;

    @Autowired
    ClsEmployeeRepo clsEmployeeRepo;

    @Autowired
    ClsPositionRepo clsPositionRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    ClsOrganizationRepo clsOrganizationRepo;

    @Autowired
    ClsEmployeeStatusRepo clsEmployeeStatusRepo;

//    @Transactional("psqlLexproEntityManager")
    public void saveClsOrganization(List<ClsOrganization> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        try{
            transaction.begin();

            List<ClsOrganization> parentList = list.stream()
                .filter(obj -> obj.getIdParent() == null)
                .map(obj -> {
                    obj.setFullName(obj.getName());
                    obj.setEmail("mail@govrb.ru");
                    obj.setOrganizationType(clsOrganizationTypeRepo.getOne(1L));
                    obj.setPath(obj.getId().toString());
                    obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));
                    obj.setIsActivated(false);
                    obj.setIsDeleted(false);

                    ClsOrganization parent = psqlLexproEntityManager.merge(obj);

                    parent.setPath(parent.getId().toString());

                    parent = psqlLexproEntityManager.merge(parent);

                    parent.setOldId(obj.getId());

                    GlobalMap.orgsMap.put(obj.getId(), parent.getId());
                    
                    return parent;
                })
                .peek(obj -> System.out.println("id: " + obj.getId() + ", path: " + obj.getPath()))
                .collect(Collectors.toList());

            for(int i=0; i<parentList.size(); i++){
                int finalI = i;
                for(int j=0; j < list.size(); j++) {
                    ClsOrganization obj = list.get(j);

                    if ((obj.getIdParent() != null) && (obj.getIdParent() == parentList.get(finalI).getOldId())) {

                        System.out.println("id: " + obj.getId() + ", parentId: " + parentList.get(finalI).getId());
                        obj.setIdParent(parentList.get(finalI).getId());
                        obj.setEmail("mail@govrb.ru");
                        obj.setFullName(obj.getName());
                        obj.setOrganizationType(clsOrganizationTypeRepo.getOne(2L));
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));
                        obj.setIsActivated(false);
                        obj.setIsDeleted(false);

                        ClsOrganization childOrg = psqlLexproEntityManager.merge(obj);

                        childOrg.setPath(parentList.get(finalI).getId().toString().concat(".").concat(childOrg.getId().toString()));

                        psqlLexproEntityManager.merge(childOrg);

                        GlobalMap.orgsMap.put(obj.getId(), childOrg.getId());
                    }
                }
            }
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public List<ClsOrganization> convertOrgEntities(List<? extends Object> list){
        return list.stream()
            .filter(obj -> ((OrgEntity) obj).getFlagOrg().equalsIgnoreCase("T") || ((OrgEntity) obj).getFlagDep().equalsIgnoreCase("T"))
            .map(
                obj -> {
                    if(obj instanceof OrgEntity){
                        return convertOrgEntityToClsOrganization((OrgEntity) obj);
                    }
                    return null;
                }
            )
            .distinct()
        .collect(Collectors.toList());
    }

    private ClsOrganization convertOrgEntityToClsOrganization(OrgEntity orgEntity){
        return ClsOrganization.builder()
                .id(orgEntity.getId())
                .email(orgEntity.getEmail())
                .fullName(orgEntity.getName())
                .name(orgEntity.getName())
                .idParent(orgEntity.getPersonEntity() != null ? orgEntity.getPersonEntity().getOrgId() : null)
                .build();
    }


    //*********************** PERSON *************************

    public List<ClsEmployee> convertPersonEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof PersonEntity){
                                return convertPersonEntityToClsEmployee((PersonEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsEmployee convertPersonEntityToClsEmployee(PersonEntity personEntity){

        String[] fio = {"", "", ""};

        fio = personEntity.getFio().trim().replaceAll("  ", " ").split("\\s|\\.");

        if(fio.length > 0) {
            for (int i = 0; i<fio.length; i++){
                System.out.println(fio[i]);
            }

            return ClsEmployee.builder()
                    .id(personEntity.getId())
                    .email(personEntity.getEmail())
                    .lastname(fio[0])
                    .firstname(fio.length > 1 ? fio[1] : null)
                    .patronymic(fio.length > 2 ? fio[2] : null)
                    .build();

        } else {

            return null;

        }
    }

    public void saveClsEmployee(List<ClsEmployee> list, List<PersonEntity> personEntities){

        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

        try{
            list.stream()
                .filter(obj -> obj != null)
                .forEach(obj -> {
                    obj.setEmail(obj.getEmail() != null ? obj.getEmail() : "default@mail.govrb.ru");
                    obj.setIsDeleted(false);
                    obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                    ClsEmployee employee = psqlLexproEntityManager.merge(obj);

                    GlobalMap.personMap.put(obj.getId(), employee.getId());

                    System.out.println("id: " + obj.getId() + ", oldId: " + GlobalMap.personMap.get(obj.getId()));

                    PersonEntity person = personEntities.stream()
                            .filter(prs -> prs.getId() == obj.getId())
                            .findFirst().get();

                    //TODO save reg_organization__employee
                    RegOrganizationEmployee regOrganizationEmployee = RegOrganizationEmployee.builder()
                            .organization(clsOrganizationRepo.getOne(GlobalMap.orgsMap.get(person.getOrgId())))
                            .employee(employee)
                            .typePosition(clsPositionRepo.getOne(GlobalMap.positionMap.get(person.getDoljnostId())))
                            .employeeStatus(clsEmployeeStatusRepo.getOne(1L))
                            .timeCreate(new Timestamp(System.currentTimeMillis()))
                            .build();

                    psqlLexproEntityManager.persist(regOrganizationEmployee);

                });

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
    }

    //*********************** USERS *************************

    public List<ClsUser> convertUsersEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof UsersEntity){
                                return convertUsersEntityToClsUser((UsersEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsUser convertUsersEntityToClsUser(UsersEntity usersEntity){

        ClsEmployee empl = clsEmployeeRepo.getOne(GlobalMap.personMap.get(usersEntity.getPersonEntity().getId()));

        if(empl != null) {
            return ClsUser.builder()
                    .id(usersEntity.getUsersGuid())
                    .lastname(empl.getLastname())
                    .firstname(empl.getFirstname())
                    .patronymic(empl.getPatronymic())
                    .login(usersEntity.getLogin())
                    .idEmployee(empl.getId())
                    .build();
        } else {
            return null;
        }
    }

    public void saveClsUser(List<ClsUser> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

        try{
            list.stream()
                .filter(obj -> obj != null)
                .forEach(obj -> {
                    obj.setIsDeleted(false);
                    obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                    ClsUser user = psqlLexproEntityManager.merge(obj);

                    RegEmployeeUser regEmployeeUser = RegEmployeeUser.builder()
                            .user(user)
                            .employee(clsEmployeeRepo.getOne(user.getIdEmployee()))
                            .timeCreate(new Timestamp(System.currentTimeMillis()))
                            .build();

                    psqlLexproEntityManager.persist(regEmployeeUser);
                });

           transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
    }

    //*********************** SP_DOLJNOST *************************

    public List<ClsPosition> convertDoljnEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof SpDoljnostEntity){
                                return convertDoljnEntityToClsPosition((SpDoljnostEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsPosition convertDoljnEntityToClsPosition(SpDoljnostEntity spDoljnostEntity){
        String code = StrUtils.transliterate(spDoljnostEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsPosition.builder()
                .id(spDoljnostEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(spDoljnostEntity.getName())
                .isDeputyPosition(false)
                .isDeleted(false)
                .build();
    }

    public void saveClsPosition(List<ClsPosition> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

        try {
            list.stream()
                    .filter(obj -> obj != null)
                    //TODO возможно надо удалять повторы по полю code?
                    .forEach(obj -> {
                        obj.setIsDeleted(false);
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                        ClsPosition clsPosition = psqlLexproEntityManager.merge(obj);

                        GlobalMap.positionMap.put(obj.getId(), clsPosition.getId());
                    });

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
    }


}
