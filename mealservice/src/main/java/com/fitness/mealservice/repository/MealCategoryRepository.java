package com.fitness.mealservice.repository;

import com.fitness.mealservice.model.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCategoryRepository extends JpaRepository<MealCategory, Long> {
}
