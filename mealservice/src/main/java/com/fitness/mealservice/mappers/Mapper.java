package com.fitness.mealservice.mappers;

import com.fitness.mealservice.dto.request.IngredientRequestDto;
import com.fitness.mealservice.model.Ingredient;

import java.util.List;

public interface Mapper<ReqDto, ResDto, Entity> {
    Entity toEntity(ReqDto dto);

    ResDto toResponseDto(Entity entity);

    List<Entity> toEntityList(List<ReqDto> dtoList);

    List<ResDto> toResponseDtoList(List<Entity> entities);
}
