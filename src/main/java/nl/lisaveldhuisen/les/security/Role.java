package nl.lisaveldhuisen.les.security;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="roles")
public class Role {
    public Role(UUID id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
    public Role() {
    }

    @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable=false, unique=true)
        private String name;

        @ManyToMany(mappedBy="roles")
        private List<User> users;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
