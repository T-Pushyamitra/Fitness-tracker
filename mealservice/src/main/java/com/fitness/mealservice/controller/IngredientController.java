package com.fitness.mealservice.controller;

import com.fitness.mealservice.dto.request.IngredientRequestDto;
import com.fitness.mealservice.dto.response.IngredientResponseDto;
import com.fitness.mealservice.mappers.ApiResponse;
import com.fitness.mealservice.model.Ingredient;
import com.fitness.mealservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ApiResponse create(@RequestBody List<IngredientRequestDto> ingredients) {
        try {
            List<IngredientResponseDto> saved = ingredientService.saveAll(ingredients);
            return ApiResponse.builder()
                    .message("Saved ingredients")
                    .data(saved)
                    .status(HttpStatus.OK.value()).build();
        } catch (IllegalArgumentException e) {
            return ApiResponse.builder()
                            .message(e.getMessage())
                            .data(null)
                            .status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @GetMapping
    public ApiResponse index(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        List<IngredientResponseDto> ingredients = ingredientService.list(sort);

        if (ingredients == null) {
            return ApiResponse.builder()
                    .message("No Ingredients found")
                    .data(null)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
        }

        return ApiResponse.builder()
                .message("Retrieved all ingredients")
                .data(ingredients)
                .status(HttpStatus.OK.value()).build();
    }
}
