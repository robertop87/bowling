package com.alenasoft;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerGame {

  final private String name;
  final private List<String> inputScores;

  private List<Integer> scores;
  private List<Frame> frames;

  public PlayerGame(String name) {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
    this.calculateScores();
  }

  public void calculateScores() {
    this.scores = this.inputScores.stream()
        .map(ScoreParser::parseToNumericScore)
        .collect(Collectors.toList());
    this.frames = FrameOrganizer.organizeScores(this.scores);
    ScoreComputer.computeScore(this.frames);
  }

  public List<String> getInputScores() {
    return this.inputScores;
  }

  public List<Integer> getScores() {
    return this.scores;
  }

  public String getName() {
    return this.name;
  }

  private String pinfallsRowToPrint() {
    String rowName = "Pinfalls";
    String framePoints =
        this.frames.stream().map(f -> f.pointsToPrint()).collect(Collectors.joining("\t\t"));

    return String.join("\t", rowName, framePoints);
  }

  private String scoresRowToPrint() {
    String rowName = "Score";
    String frameScores =
        this.frames
            .stream()
            .map(f -> String.format("%5d", f.getScore()))
            .collect(Collectors.joining("\t\t"));

    return String.join("\t\t", rowName, frameScores);
  }

  @Override
  public String toString() {
    return String.join("\n", this.name,
        this.pinfallsRowToPrint(), this.scoresRowToPrint());
  }
}
