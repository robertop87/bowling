package com.alenasoft.infrastructure;

import com.alenasoft.domain.PlayerGame;
import java.util.List;

public interface OutputPrinter {

  void print(List<PlayerGame> playerGames);
}
