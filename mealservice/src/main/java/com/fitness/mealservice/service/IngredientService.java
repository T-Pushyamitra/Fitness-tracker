package com.fitness.mealservice.service;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {

        if (ingredientRepository.existsByName(ingredient.getName())){
            throw new IllegalArgumentException(ingredient.getName() + " already exists!");
        }

        Ingredient _ingredient = (ingredient.getId() != null) ?
                    ingredientRepository.getReferenceById(ingredient.getId()): new Ingredient();

        _ingredient.setName(ingredient.getName());
        _ingredient.setQuantity(ingredient.getQuantity());
        _ingredient.setUnit(ingredient.getUnit());
        _ingredient.setCalories(ingredient.getCalories());
        _ingredient.setProtein(ingredient.getProtein());
        _ingredient.setCarbs(ingredient.getCarbs());
        _ingredient.setFat(ingredient.getFat());
        _ingredient.setFiber(ingredient.getFiber());

        return ingredientRepository.save(_ingredient);
    }

    public List<Ingredient> list(Sort sort) {
        List<Ingredient> list = ingredientRepository.findAll(sort);
        return (list.isEmpty()) ? null: list;
    }
}
