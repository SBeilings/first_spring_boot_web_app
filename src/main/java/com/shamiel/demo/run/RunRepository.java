package com.shamiel.demo.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList();

    void update(Run run, Integer id){
        Optional<Run> existingRun = findByID(id);
        if (existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()),run);
        }

    }

    void delete(Integer id){
        runs.removeIf(run -> run.ID().equals(id));
    }

    void create(Run run){
        runs.add(run);
    }

    List<Run> findall(){
        return runs;
    }

    Optional<Run> findByID(Integer id){
        return runs.stream()
                .filter(run -> run.ID() == id)
                .findFirst();
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 7, Location.INDOOR));
        runs.add(new Run(2, "Third Run", LocalDateTime.now(), LocalDateTime.now().plus(3, ChronoUnit.HOURS), 9, Location.OUTDOOR));
    }

}
