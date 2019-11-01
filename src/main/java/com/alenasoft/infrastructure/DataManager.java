package com.alenasoft.infrastructure;

import com.alenasoft.ScoreCalculator;
import com.alenasoft.domain.PlayerGame;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

  private List<PlayerGame> playerGames;
  private ScoreCalculator scoreCalculator;

  public DataManager() {
    this.playerGames = new ArrayList<>();
    this.scoreCalculator = ScoreCalculator.defaultScoreCalculator();
  }

  public List<PlayerGame> getPlayerGames() {
    return this.playerGames;
  }

  public void processData(List<PlayerGame> playerGames) {
    this.playerGames = playerGames;
    for (PlayerGame playerGame : this.playerGames) {
      this.scoreCalculator.calculateScore(playerGame);
    }
  }
}
