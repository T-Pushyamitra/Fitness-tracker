package com.fitness.mealservice.mappers;

import com.fitness.mealservice.dto.request.IngredientRequestDto;
import com.fitness.mealservice.dto.response.IngredientResponseDto;
import com.fitness.mealservice.model.Ingredient;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class IngredientMapper implements Mapper<IngredientRequestDto, IngredientResponseDto, Ingredient> {

    @Override
    public Ingredient toEntity(IngredientRequestDto ingredientRequestDto) {
        if (ingredientRequestDto == null) return null;

        return Ingredient.builder()
                .id(ingredientRequestDto.getId())
                .name(ingredientRequestDto.getName())
                .unit(ingredientRequestDto.getUnit())
                .calories(ingredientRequestDto.getCalories())
                .fiber(ingredientRequestDto.getFiber())
                .protein(ingredientRequestDto.getProtein())
                .carbs(ingredientRequestDto.getCarbs())
                .fats(ingredientRequestDto.getFats())
                .quantity(ingredientRequestDto.getQuantity())
                .build();
    }

    @Override
    public IngredientResponseDto toResponseDto(Ingredient ingredient) {
        if (ingredient == null) return null;

        return IngredientResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnit())
                .calories(ingredient.getCalories())
                .fiber(ingredient.getFiber())
                .protein(ingredient.getProtein())
                .carbs(ingredient.getCarbs())
                .fats(ingredient.getFats())
                .quantity(ingredient.getQuantity())
                .build();
    }

    @Override
    public List<Ingredient> toEntityList(List<IngredientRequestDto> ingredientRequestDtos) {
        if (ingredientRequestDtos == null) return Collections.emptyList();
        return ingredientRequestDtos.stream().map(this::toEntity).toList();
    }

    @Override
    public List<IngredientResponseDto> toResponseDtoList(List<Ingredient> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(this::toResponseDto).toList();
    }

}
