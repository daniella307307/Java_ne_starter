package com.example.demo.models;

import com.example.demo.enumerations.role.EUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Enumerated(EnumType.STRING)
    @Column(name="name", unique = true,nullable = false)
    private EUserRole name;
}
