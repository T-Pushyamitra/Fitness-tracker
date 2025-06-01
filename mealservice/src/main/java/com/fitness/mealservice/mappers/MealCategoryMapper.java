package com.fitness.mealservice.mappers;

import com.fitness.mealservice.dto.request.MealCategoryRequestDto;
import com.fitness.mealservice.dto.response.MealCategoryResponseDto;
import com.fitness.mealservice.model.MealCategory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MealCategoryMapper implements Mapper<MealCategoryRequestDto, MealCategoryResponseDto, MealCategory> {

    @Override
    public MealCategory toEntity(MealCategoryRequestDto mealCategoryRequestDto) {
        if (mealCategoryRequestDto == null) return null;

        return MealCategory.builder()
                .id(mealCategoryRequestDto.getId())
                .name(mealCategoryRequestDto.getName())
                .description(mealCategoryRequestDto.getDescription())
                .build();
    }

    @Override
    public MealCategoryResponseDto toResponseDto(MealCategory mealCategory) {
        if (mealCategory == null) return null;

        return MealCategoryResponseDto.builder()
                .id(mealCategory.getId())
                .name(mealCategory.getName())
                .description(mealCategory.getDescription())
                .build();
    }

    @Override
    public List<MealCategory> toEntityList(List<MealCategoryRequestDto> mealCategoryRequestDtos) {
        if(mealCategoryRequestDtos == null) return null;

        return mealCategoryRequestDtos.stream().map(this::toEntity).toList();
    }

    @Override
    public List<MealCategoryResponseDto> toResponseDtoList(List<MealCategory> mealCategories) {
        if (mealCategories == null) return null;

        return mealCategories.stream().map(this::toResponseDto).toList();
    }
}
