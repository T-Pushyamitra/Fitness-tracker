package com.fitness.mealservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double quantity; // e.g., 100.0

    @Column(nullable = false)
    private String unit;     // e.g., grams, ml

    @Column(nullable = false)
    private double calories;

    @Column(nullable = false)
    private double protein;   // grams

    @Column(nullable = false)
    private double carbs;     // grams

    @Column(nullable = false)
    private double fats;       // grams

    @Column(nullable = false)
    private double fiber;     // grams
}
