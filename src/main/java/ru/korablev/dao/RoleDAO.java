package ru.korablev.dao;

import ru.korablev.model.Role;
import ru.korablev.util.AuthorityRole;

public interface RoleDAO {

    Role findRoleByName(AuthorityRole role);

    boolean isExistRole(AuthorityRole role);

}