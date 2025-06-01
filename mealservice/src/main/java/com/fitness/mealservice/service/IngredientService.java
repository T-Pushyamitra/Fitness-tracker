package com.fitness.mealservice.service;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.repository.IngredientRepository;
import com.fitness.mealservice.utils.validators.IngredientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientValidator validator;

    private Ingredient save(Ingredient ingredient){
        if (validator.validate(ingredient)) {

            Ingredient _ingredient = new Ingredient();

            _ingredient.setName(ingredient.getName());
            _ingredient.setQuantity(ingredient.getQuantity());
            _ingredient.setUnit(ingredient.getUnit());
            _ingredient.setCalories(ingredient.getCalories());
            _ingredient.setProtein(ingredient.getProtein());
            _ingredient.setCarbs(ingredient.getCarbs());
            _ingredient.setFats(ingredient.getFats());
            _ingredient.setFiber(ingredient.getFiber());
            ingredientRepository.save(_ingredient);
        }
        return ingredient;
    }

    private Ingredient update(Ingredient ingredient) {
        if (validator.validate(ingredient, true)) {

            Ingredient _ingredient = ingredientRepository.getReferenceById(ingredient.getId());

            _ingredient.setName(ingredient.getName());
            _ingredient.setQuantity(ingredient.getQuantity());
            _ingredient.setUnit(ingredient.getUnit());
            _ingredient.setCalories(ingredient.getCalories());
            _ingredient.setProtein(ingredient.getProtein());
            _ingredient.setCarbs(ingredient.getCarbs());
            _ingredient.setFats(ingredient.getFats());
            _ingredient.setFiber(ingredient.getFiber());

            ingredientRepository.save(_ingredient);
        }
        return ingredient;
    }

    public List<Ingredient> saveAll(List<Ingredient> ingredients) {

        // Return Null
        if (ingredients == null) {
            return null;
        }

        ingredients.forEach(ingredient -> {
            Ingredient _ingredient = (ingredient.getId() != null) ? update(ingredient) : save(ingredient);
        });

        return ingredients;
    }

    public List<Ingredient> list(Sort sort) {
        List<Ingredient> list = ingredientRepository.findAll(sort);
        return (list.isEmpty()) ? null: list;
    }
}
