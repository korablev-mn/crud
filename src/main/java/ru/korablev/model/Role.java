package ru.korablev.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String role;

//    @ManyToMany(mappedBy = "roles")
//    @JoinColumn(name = "user_id")
    @ManyToMany(mappedBy = "roles")
    private Set<User> user = new HashSet<>();

//    public Set<User> getUser() {
//        return users;
//    }

//    public void setUser(Set<User> user) {
//        this.users = user;
//    }

    public Long getId() {
        return id;
    }

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role) &&
                Objects.equals(user, role1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, user);
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
