package com.fitness.mealservice.utils.validators;

import com.fitness.mealservice.model.Ingredient;

public interface Validator<T, Boolean> {

    Boolean validate(T entity);
    Boolean validate(T entity, Boolean cond);
}
