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
    
    public Games getByName(String name) {
        return gamesRepository.findByName(name);
    }
    
    public Games getById(long id) {
        return gamesRepository.findById(id);
    }
    public void save(Games game) {
        gamesRepository.save(game);
    }
    
    public void updateNextPlayer(String name,String nextPlayer){
        Games game = getByName(name);
        gamesRepository.delete(game);
        game.setNextPlayer(nextPlayer);
        gamesRepository.save(game);
    }
        
        
    }
