package com.fitness.mealservice.service;

import com.fitness.mealservice.dto.request.IngredientRequestDto;
import com.fitness.mealservice.dto.response.IngredientResponseDto;
import com.fitness.mealservice.mappers.IngredientMapper;
import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.repository.IngredientRepository;
import com.fitness.mealservice.utils.validators.IngredientValidator;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientValidator validator;

    @Autowired
    private IngredientMapper mapper;

    private IngredientResponseDto save(Ingredient ingredient){

        Ingredient _ingredient = (ingredient.getId() != null) ?
                ingredientRepository.getReferenceById(ingredient.getId()) : new Ingredient();

        if (validator.validate(ingredient, _ingredient.getId() != null)) {

            _ingredient.setName(ingredient.getName());
            _ingredient.setQuantity(ingredient.getQuantity());
            _ingredient.setUnit(ingredient.getUnit());
            _ingredient.setCalories(ingredient.getCalories());
            _ingredient.setProtein(ingredient.getProtein());
            _ingredient.setCarbs(ingredient.getCarbs());
            _ingredient.setFats(ingredient.getFats());
            _ingredient.setFiber(ingredient.getFiber());

            _ingredient = ingredientRepository.save(_ingredient);
        }
        return mapper.toResponseDto(_ingredient);
    }

    public List<IngredientResponseDto> saveAll(List<IngredientRequestDto> ingredients) {

        if (ingredients == null) return null;

        List<Ingredient> list =  mapper.toEntityList(ingredients);
        return list.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    public List<IngredientResponseDto> list(Sort sort) {
        List<IngredientResponseDto> list = mapper.toResponseDtoList(ingredientRepository.findAll(sort));
        return list;
    }
}
