package ru.korablev.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korablev.dao.RoleDAO;
import ru.korablev.model.Role;
import ru.korablev.service.RoleService;
import ru.korablev.util.AuthorityRole;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getRoleByName(AuthorityRole role) {
        try {
            Role roleUser = roleDAO.findRoleByName(role);
            if (roleUser == null) {
                return new Role(role);
            }
            return roleUser;
        } catch (Exception e) {
            return new Role(role);
        }
    }
}