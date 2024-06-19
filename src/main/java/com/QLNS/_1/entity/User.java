package com.QLNS._1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "user"
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "username",
            length = 50,
            unique = true
    )
    private @NotBlank(
            message = "Username is required"
    ) @Size(
            min = 1,
            max = 50,
            message = "Username must be between 1 and 50 characters"
    ) String username;
    @Column(
            name = "password",
            length = 250
    )
    private @NotBlank(
            message = "Password is required"
    ) String password;
    @Column(
            name = "fullname",
            length = 100
    )
    private @NotBlank(
            message = "Fullname is required"
    ) @Size(
            min = 1,
            max = 100,
            message = "Fullname must be between 1 and 100 characters"
    ) String fullname;
    @Column(
            name = "email",
            length = 50,
            unique = true
    )
    private @NotBlank(
            message = "Email is required"
    ) @Size(
            min = 1,
            max = 50,
            message = "Email must be between 1 and 50 characters"
    ) @Email String email;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )}
    )
    private Set<Role> roles = new HashSet();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection)this.roles.stream().map((role) -> {
            return new SimpleGrantedAuthority(role.getName());
        }).collect(Collectors.toSet());
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && Hibernate.getClass(this) == Hibernate.getClass(o)) {
            User user = (User)o;
            return this.id != null && Objects.equals(this.id, user.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getEmail() {
        return this.email;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "User(id=" + var10000 + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", fullname=" + this.getFullname() + ", email=" + this.getEmail() + ", roles=" + String.valueOf(this.getRoles()) + ")";
    }

    public User() {
    }

    public User(final Long id, final String username, final String password, final String fullname, final String email, final Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.roles = roles;
    }

    public static class UserBuilder {
        private Long id;
        private String username;
        private String password;
        private String fullname;
        private String email;
        private Set<Role> roles;

        UserBuilder() {
        }

        public UserBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder fullname(final String fullname) {
            this.fullname = fullname;
            return this;
        }

        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder roles(final Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(this.id, this.username, this.password, this.fullname, this.email, this.roles);
        }

        public String toString() {
            Long var10000 = this.id;
            return "User.UserBuilder(id=" + var10000 + ", username=" + this.username + ", password=" + this.password + ", fullname=" + this.fullname + ", email=" + this.email + ", roles=" + String.valueOf(this.roles) + ")";
        }
    }
}