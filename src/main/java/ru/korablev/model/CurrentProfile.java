package ru.korablev.model;

import java.util.Set;

public class CurrentProfile extends org.springframework.security.core.userdetails.User {
    private final Long id;
    private final String name;
    private final Set<Role> role;


    public CurrentProfile(ru.korablev.model.User user) {
        super(user.getLogin(), user.getPassword(), true, true, true, true, user.getRoles());
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRoles();
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
            str.append("-");
            str.append(role.getRole());
        }
        return String.valueOf(str);
    }
}