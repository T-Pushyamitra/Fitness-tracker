package com.fitness.mealservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientResponseDto {

    private Long id;
    private String name;
    private double quantity;
    private String unit;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;
    private double fiber;
}
