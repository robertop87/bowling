package com.alenasoft;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerGame {

  final private String name;
  final private List<String> inputScores;
  final private List<Integer> scores;

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
    this.scores = inputScores.stream()
        .map(ScoreParser::parseToNumericScore)
        .collect(Collectors.toList());
  }

  public List<Integer> getScores() {
    return this.scores;
  }
}
