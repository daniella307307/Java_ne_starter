package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Book  extends Base{
    @Column(unique = true, nullable = false)
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int year;

    @ManyToOne()
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}
