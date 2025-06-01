package com.fitness.mealservice.utils.validators;

import com.fitness.mealservice.model.MealCategory;
import com.fitness.mealservice.repository.MealCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MealCategoryValidator implements Validator<MealCategory, Boolean>{

    private final MealCategoryRepository mealCategoryRepository;

    @Override
    public Boolean validate(MealCategory mealCategory) {

        if (mealCategoryRepository.existsByName(mealCategory.getName())){
            throw new IllegalArgumentException(mealCategory.getName() + " already exists!");
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean validate(MealCategory mealCategory, Boolean isUpdate) {
        return Boolean.TRUE;
    }

}
