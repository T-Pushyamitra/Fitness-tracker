package com.fitness.mealservice.controller;

import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Ingredient ingredient) {
        try {
            Ingredient _ingredient = ingredientService.save(ingredient);
            return ResponseEntity.ok(_ingredient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> index(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        List<Ingredient> ingredients = ingredientService.list(sort);
        if (ingredients == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(ingredients);
    }

}
