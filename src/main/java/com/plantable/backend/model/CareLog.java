package com.plantable.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "care_logs")
public class CareLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_plant_id", nullable = false)
    @JsonIgnore
    private UserPlant userPlant;

    @Enumerated(EnumType.STRING)
    @Column(name = "care_type", nullable = false, length = 30)
    private CareType careType;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false, length = 10)
    private CareAction action;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    public CareLog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserPlant getUserPlant() { return userPlant; }
    public void setUserPlant(UserPlant userPlant) { this.userPlant = userPlant; }

    public CareType getCareType() { return careType; }
    public void setCareType(CareType careType) { this.careType = careType; }

    public CareAction getAction() { return action; }
    public void setAction(CareAction action) { this.action = action; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
