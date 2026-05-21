package com.plantable.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonBackReference
    private Plant plant;

    public Tag() {}

    public Tag(String name, Plant plant) {
        this.name = name;
        this.plant = plant;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
}
