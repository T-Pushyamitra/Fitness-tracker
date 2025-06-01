package com.fitness.mealservice.service;

import com.fitness.mealservice.dto.request.MealCategoryRequestDto;
import com.fitness.mealservice.dto.response.MealCategoryResponseDto;
import com.fitness.mealservice.mappers.MealCategoryMapper;
import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.model.MealCategory;
import com.fitness.mealservice.repository.MealCategoryRepository;
import com.fitness.mealservice.utils.validators.MealCategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealCategoryService {
    private final MealCategoryRepository mealCategoryRepository;
    private final MealCategoryValidator validator;

    @Autowired
    private MealCategoryMapper mapper;

    private MealCategoryResponseDto save(MealCategory mealCategory) {
        MealCategory _mealCategory = (mealCategory.getId() != null)?
                mealCategoryRepository.getReferenceById(mealCategory.getId()) : new MealCategory();

        if (validator.validate(mealCategory, _mealCategory.getId() != null)) {

            _mealCategory.setName(mealCategory.getName());
            _mealCategory.setDescription(mealCategory.getDescription());
            _mealCategory = mealCategoryRepository.save(_mealCategory);
        }
        return mapper.toResponseDto(_mealCategory);
    }


    public List<MealCategoryResponseDto> saveAll(List<MealCategoryRequestDto> mealCategories) {

        if (mealCategories == null) return null;

        List<MealCategory> mealCategoryList = mapper.toEntityList(mealCategories);
        return  mealCategoryList.stream()
                .map(this::save)
                .collect(Collectors.toList());

    }

    public List<MealCategoryResponseDto> list(Sort sort) {
        List<MealCategoryResponseDto> list = mapper.toResponseDtoList(mealCategoryRepository.findAll(sort));
        return list;
    }

}
