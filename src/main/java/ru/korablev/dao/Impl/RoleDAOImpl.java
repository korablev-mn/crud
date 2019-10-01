package ru.korablev.dao.Impl;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.korablev.dao.RoleDAO;
import ru.korablev.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String role) {
        try {
            //noinspection JpaQlInspection
            Query query = (Query) entityManager.createQuery("select u from Role u where u.role = :role");
            query.setParameter("role", role);
            Role roleUser = (Role) query.getSingleResult();
            return roleUser;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isExistRole(String role) {
        if (findRoleByName(role) != null) {
            return true;
        }
        return false;
    }
}