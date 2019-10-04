package ru.korablev.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korablev.dao.RoleDAO;
import ru.korablev.model.Role;
import ru.korablev.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getRoleByName(String role) {
            return roleDAO.findRoleByName(role);
    }
}