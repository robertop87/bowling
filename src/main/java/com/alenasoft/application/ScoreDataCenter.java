package com.alenasoft.application;

import com.alenasoft.domain.PlayerGame;
import java.util.List;

public interface ScoreDataCenter {

  List<PlayerGame> getPlayerGames();
  void processData();
}
