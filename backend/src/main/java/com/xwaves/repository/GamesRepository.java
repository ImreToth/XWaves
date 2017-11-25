package com.xwaves.repository;

import com.xwaves.domain.Games;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface GamesRepository extends CrudRepository<Games, Long> {
    Games findById(long id);
    
    Games findByName(String name);
    
    List<Games> findAll();
    
}
