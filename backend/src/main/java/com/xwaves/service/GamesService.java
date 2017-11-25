package com.xwaves.service;

import com.xwaves.domain.Games;
import com.xwaves.repository.GamesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
    
    private GamesRepository gamesRepository;
    
    @Autowired
    public GamesService(GamesRepository gamesRepository){
        this.gamesRepository = gamesRepository;
    }
    
    public List<Games> getAll() {
        return gamesRepository.findAll();
    }
    
    public Games getByName(String Heroname) {
        return gamesRepository.findByName(Heroname);
    }
    
    public Games getById(long id) {
        return gamesRepository.findById(id);
    }
    public void save(Games games) {
        gamesRepository.save(games);
    }    
}
