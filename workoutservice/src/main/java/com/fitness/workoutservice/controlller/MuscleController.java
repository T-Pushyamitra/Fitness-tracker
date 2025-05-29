package com.fitness.workoutservice.controlller;

import com.fitness.workoutservice.model.Muscle;
import com.fitness.workoutservice.service.MuscleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muscle")
public class MuscleController {
    @Autowired
    public MuscleService muscleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<Muscle> muscles) {
        try {
            List<Muscle> muscleList = muscleService.saveAll(muscles);
            return ResponseEntity.ok(muscleList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Muscle>> index(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        List<Muscle> muscles = muscleService.list(sort);
        if (muscles == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(muscles);
    }
}
