package com.fitness.workoutservice.service;

import com.fitness.workoutservice.Repository.MuscleRepository;
import com.fitness.workoutservice.model.Muscle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuscleService {

    @Autowired
    public MuscleRepository muscleRepository;

    private Muscle save(Muscle muscle){
        Muscle _muscle = new Muscle();
        if (muscleRepository.existsByName(muscle.getName())) {
            throw new IllegalArgumentException(muscle.getName() + " already exists!");
        }
        _muscle.setName(muscle.getName());
        _muscle.setDescription(muscle.getDescription());
        muscleRepository.save(_muscle);
        return _muscle;
    }

    private Muscle update(Muscle muscle){
        Muscle _muscle = muscleRepository.getReferenceById(muscle.getId());
        _muscle.setName(muscle.getName());
        _muscle.setDescription(muscle.getDescription());
        muscleRepository.save(_muscle);
        return _muscle;
    }

    public List<Muscle> saveAll(List<Muscle> muscles) {

        // Return Null
        if (muscles == null) {
            return null;
        }

        muscles.forEach(muscle -> {
            Muscle _muscle = (muscle.getId() != null) ? update(muscle) : save(muscle);
        });

        return muscles;
    }
    public List<Muscle> list(Sort sort) {
        List<Muscle> list = muscleRepository.findAll(sort);
        return (list.isEmpty()) ? null: list;
    }
}
