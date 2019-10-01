package ru.korablev.dao;

import ru.korablev.model.Role;

public interface RoleDAO {

    Role findRoleByName(String role);

    boolean isExistRole(String role);
}