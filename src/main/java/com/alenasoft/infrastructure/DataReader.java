package com.alenasoft.infrastructure;

import com.alenasoft.domain.PlayerGame;
import java.util.List;

public interface DataReader {

  List<PlayerGame> readPlayerGames();
}
