package com.fitness.mealservice.service;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.model.MealCategory;
import com.fitness.mealservice.repository.MealCategoryRepository;
import com.fitness.mealservice.utils.validators.MealCategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealCategoryService {
    private final MealCategoryRepository mealCategoryRepository;
    private final MealCategoryValidator validator;

    private MealCategory save(MealCategory mealCategory) {
        if (validator.validate(mealCategory)) {

            MealCategory _mealCategory = new MealCategory();

            _mealCategory.setName(mealCategory.getName());
            _mealCategory.setDescription(mealCategory.getDescription());
            mealCategoryRepository.save(_mealCategory);
        }
        return mealCategory;
    }

    private MealCategory update(MealCategory mealCategory) {
        if (validator.validate(mealCategory, true)) {

            MealCategory _mealCategory = mealCategoryRepository.getReferenceById(mealCategory.getId());

            _mealCategory.setName(mealCategory.getName());
            _mealCategory.setDescription(mealCategory.getDescription());
            mealCategoryRepository.save(_mealCategory);
        }
        return mealCategory;
    }

    public List<MealCategory> saveAll(List<MealCategory> mealCategories) {

        // Return Null
        if (mealCategories == null) {
            return null;
        }

        mealCategories.forEach(mealCategory -> {
            MealCategory _mealCategory = (mealCategory.getId() != null) ? update(mealCategory) : save(mealCategory);
        });

        return mealCategories;
    }

    public List<MealCategory> list(Sort sort) {
        List<MealCategory> list = mealCategoryRepository.findAll(sort);
        return (list.isEmpty()) ? null: list;
    }

}
