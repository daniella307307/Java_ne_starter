package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product extends Base{
    private float price;
    private String name;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}
