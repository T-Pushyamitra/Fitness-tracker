package com.fitness.mealservice.controller;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> create(@RequestBody Ingredient ingredient){
        Ingredient _ingredient = ingredientService.save(ingredient);
        return ResponseEntity.ok(_ingredient);
    }


}
