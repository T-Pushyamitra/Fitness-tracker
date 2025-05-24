package com.fitness.mealservice.repository;

import com.fitness.mealservice.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
