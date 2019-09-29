package ru.korablev.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CurrentProfile extends org.springframework.security.core.userdetails.User {
    private final Long id;
    private final String name;
    private final Set<Role> role;


    public CurrentProfile(ru.korablev.model.User user) {//Collections.singleton(new SimpleGrantedAuthority(user.getRole())   AuthorityRoleClass(user.getRole()) Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        super(user.getLogin(), user.getPassword(), true, true, true, true, user.getRole());
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }

    private static Collection<? extends GrantedAuthority> AuthorityRoleClass(Set<Role> roles) {
        Set<GrantedAuthority> setRole = new HashSet<>();
        for (Role role : roles) {
            setRole.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return setRole;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CurrentProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Set<Role> getRole() {
        return role;
    }

    public String getRoleString() {
        StringBuilder str = new StringBuilder();
        for (Role role:this.role) {
            str.append(role.getRole());
        }
        return String.valueOf(str);
    }
}