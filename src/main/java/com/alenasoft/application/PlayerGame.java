package com.alenasoft.application;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerGame {

  public static final Logger log = LogManager.getLogger();

  private final String name;
  private final List<String> inputScores;

  private List<Frame> frames;
  private boolean validGame;

  public PlayerGame(String name) {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
    this.frames = new ArrayList<>();
  }

  public List<String> getInputScores() {
    return this.inputScores;
  }

  public String getName() {
    return this.name;
  }

  public List<Frame> getFrames() {
    return this.frames;
  }

  public void setFrames(List<Frame> frames) {
    this.frames = frames;
  }

  public void setValidGame(boolean valid) {
    this.validGame = valid;
  }

  public boolean isValidGame() {
    return this.validGame;
  }
}
