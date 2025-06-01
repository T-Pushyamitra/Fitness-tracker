package com.fitness.mealservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealCategoryRequestDto {

    private Long id;
    private String name;
    private String description;
}
