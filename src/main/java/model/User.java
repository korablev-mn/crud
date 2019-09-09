package model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    //    @Past
    //    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private Date birthday;
    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(Long id, String login, String name, String password, Date birthday, String role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public User(String login, String name, String password, Date birthday, String role) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public enum ROLE {
        ADMIN("admin"), USER("user"), UNKNOWN("guest");

        private String role;

        ROLE(String admin) {
        }

        public String getRole() {
            return role;
        }
    }

    public String getRole() {
        if (role.equals("")) {
            role = ROLE.USER.getRole();
        }
        return role;
    }

    public void setRole(String role) {
        if (role.equals(ROLE.ADMIN.getRole())) {
            this.role = ROLE.ADMIN.getRole();
        } else {
            this.role = ROLE.USER.getRole();
        }
    }
}