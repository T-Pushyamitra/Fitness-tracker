package com.fitness.mealservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealCategoryResponseDto {

    private Long id;
    private String name;
    private String description;
}
