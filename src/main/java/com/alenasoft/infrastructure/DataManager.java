package com.alenasoft.infrastructure;

import com.alenasoft.application.PlayerGame;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

  private List<PlayerGame> playerGames;

  public DataManager() {
    this.playerGames = new ArrayList<>();
  }

  public List<PlayerGame> getPlayerGames() {
    return this.playerGames;
  }

  public void processData(List<PlayerGame> playerGames) {
    this.playerGames = playerGames;
    for (PlayerGame playerGame : this.playerGames) {
      playerGame.calculateScores();
    }
  }
}
