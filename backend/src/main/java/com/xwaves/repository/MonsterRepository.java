package com.xwaves.repository;

import com.xwaves.domain.Monster;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long> {
    Monster findById(long id);
    
    Monster findByName(String name);

    List<Monster> findAll();
}
