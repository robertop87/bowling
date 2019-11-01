package com.alenasoft.application.defaults;

import com.alenasoft.application.ScoreCalculator;
import com.alenasoft.application.ScoreDataCenter;
import com.alenasoft.domain.PlayerGame;
import java.util.ArrayList;
import java.util.List;

public class DefaultScoreDataCenter implements ScoreDataCenter {

  private List<PlayerGame> playerGames;
  private ScoreCalculator scoreCalculator;

  public DefaultScoreDataCenter() {
    this(new ArrayList<>(), ScoreCalculator.defaultScoreCalculator());
  }

  public DefaultScoreDataCenter(List<PlayerGame> playerGames) {
    this(playerGames, ScoreCalculator.defaultScoreCalculator());
  }

  public DefaultScoreDataCenter(List<PlayerGame> playerGames, ScoreCalculator scoreCalculator) {
    this.playerGames = playerGames;
    this.scoreCalculator = scoreCalculator;
  }

  @Override
  public List<PlayerGame> getPlayerGames() {
    return this.playerGames;
  }

  @Override
  public void processData() {
    for (PlayerGame playerGame : this.playerGames) {
      this.scoreCalculator.calculateScore(playerGame);
    }
  }
}
