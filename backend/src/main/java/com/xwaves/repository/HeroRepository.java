package com.xwaves.repository;

import com.xwaves.domain.Hero;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface HeroRepository extends CrudRepository<Hero, Long> {
    
    Hero findById(long id);
    
    Hero findByName(String username);

    List<Hero> findAll();
    
}
