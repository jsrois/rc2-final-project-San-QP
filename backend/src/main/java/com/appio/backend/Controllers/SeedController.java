package com.appio.backend.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appio.backend.Persistence.Seed;
import com.appio.backend.Persistence.SeedRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SeedController {

    private SeedRepository repository;

    public SeedController(@Autowired SeedRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/seeds")
    public List<SeedResponse> showSeeds() {
        List<SeedResponse> seeds = new ArrayList<>();
        List<Seed> seedsInDatabaseSeeds = repository.findAll();

        for (Seed seed : seedsInDatabaseSeeds) {
            Optional<Date> pick_up_date = Optional.ofNullable(seed.getPick_up_date());
            Optional<Integer> generation = Optional.ofNullable(seed.getGeneration());
            seeds.add(new SeedResponse(seed.getId(), seed.getName(), seed.getOrigin(), pick_up_date, generation, seed.getDescription()));
        }

        return seeds;
    }
}
