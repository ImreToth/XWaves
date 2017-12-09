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
    public GamesService(GamesRepository gamesRepository) {
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

    public List<Games> getByPlayer1(String name) {

        return gamesRepository.findByPlayer1(name);
    }

    public List<Games> getByPlayer2(String name) {
        return gamesRepository.findByPlayer2(name);
    }

    public List<Games> getByPlayer3(String name) {
        return gamesRepository.findByPlayer3(name);
    }

    public List<Games> getByNextPlayer(String name) {
        return gamesRepository.findByNextPlayer(name);
    }

    public void save(Games game) {
        gamesRepository.save(game);
    }

    public void delete(Games game) {
        gamesRepository.delete(game);
    }

    public void updateNextPlayer(String name, String nextPlayer) {
        Games game = getByName(name);
        gamesRepository.delete(game);
        game.setNextPlayer(nextPlayer);
        gamesRepository.save(game);
    }

    public int joinPlayer(String username, String gamename) {
        Games game = getByName(gamename);
        if (game.getPlayer1().equals("")) {
            gamesRepository.delete(game);
            game.setPlayer1(username);
            gamesRepository.save(game);
            return 1;
        } else if (game.getPlayer2().equals("")) {
            gamesRepository.delete(game);
            game.setPlayer2(username);
            gamesRepository.save(game);
            return 2;
        } else if (game.getPlayer3().equals("")) {
            gamesRepository.delete(game);
            game.setPlayer3(username);
            gamesRepository.save(game);
            return 3;
        }
        return 0;
    }

}
