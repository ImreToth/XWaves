
package com.xwaves.service;

import com.xwaves.domain.Monster;
import com.xwaves.repository.MonsterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {
    private MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }
    
     public List<Monster> getAll() {
        return monsterRepository.findAll();
    }
    
    public Monster getByName(String name) {
        return monsterRepository.findByName(name);
    }
    
    public Monster getById(long id) {
        return monsterRepository.findById(id);
    }
        
}
