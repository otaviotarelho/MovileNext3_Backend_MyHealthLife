package edu.otaviotarelho.users.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "professional"})})
public class Relationships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user;
    private Long professional;
    private LocalDateTime creationTime;

    public Relationships() {
    }

    public Long getUser() {
        return user;
    }

    public Relationships setUser(Long user) {
        this.user = user;
        return this;
    }

    public Long getProfessional() {
        return professional;
    }

    public Relationships setProfessional(Long professional) {
        this.professional = professional;
        return this;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Relationships setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }
}
