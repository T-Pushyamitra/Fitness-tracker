package com.fitness.mealservice.utils.validators;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IngredientValidator implements Validator<Ingredient, Boolean>{

    private final IngredientRepository ingredientRepository;

    @Override
    public Boolean validate(Ingredient ingredient) {

        if (ingredientRepository.existsByName(ingredient.getName())){
            throw new IllegalArgumentException(ingredient.getName() + " already exists!");
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean validate(Ingredient ingredient, Boolean isUpdate) {
        return Boolean.TRUE;
    }

}
