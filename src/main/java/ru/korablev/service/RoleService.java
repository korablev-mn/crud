package ru.korablev.service;

import ru.korablev.model.Role;
import ru.korablev.util.AuthorityRole;

public interface RoleService {

    Role getRoleByName(AuthorityRole role);
}
