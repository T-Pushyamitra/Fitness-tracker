package com.fitness.workoutservice.Repository;

import com.fitness.workoutservice.model.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuscleRepository extends JpaRepository<Muscle,Long>{
    boolean existsByName(String name);

}
