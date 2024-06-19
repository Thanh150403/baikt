package com.QLNS._1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.catalina.User;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "role"
)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private @NotBlank(
            message = "Name is required"
    ) @Size(
            max = 50,
            message = "Name must be less than 50 characters"
    ) String name;
    @Column(
            name = "description",
            length = 250
    )
    private @Size(
            max = 250,
            message = "Description must be less than 250 characters"
    ) String description;
    @ManyToMany(
            mappedBy = "roles",
            cascade = {CascadeType.ALL}
    )
   private Set<User> users = new HashSet();

    public String getAuthority() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && Hibernate.getClass(this) == Hibernate.getClass(o)) {
            Role role = (Role)o;
            return this.getId() != null && Objects.equals(this.getId(), role.getId());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "Role(id=" + var10000 + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }

    public Role() {
    }

    public Role(final Long id, final String name, final String description, final Set<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public static class RoleBuilder {
        private Long id;
        private String name;
        private String description;
        private Set<User> users;

        RoleBuilder() {
        }

        public RoleBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public RoleBuilder users(final Set<User> users) {
            this.users = users;
            return this;
        }

        public Role build() {
            return new Role(this.id, this.name, this.description, this.users);
        }

        public String toString() {
            Long var10000 = this.id;
            return "Role.RoleBuilder(id=" + var10000 + ", name=" + this.name + ", description=" + this.description + ", users=" + String.valueOf(this.users) + ")";
        }
    }
}