package com.xwaves.service;

import com.xwaves.domain.Hero;
import com.xwaves.repository.HeroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {
    private HeroRepository heroRepository;
    
    @Autowired
    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }
    
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }
    
    public Hero getByName(String name) {
        return heroRepository.findByName(name);
    }
    
    public Hero getById(long id) {
        return heroRepository.findById(id);
    }
}
