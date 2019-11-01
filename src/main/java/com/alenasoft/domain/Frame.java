package com.alenasoft.domain;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import com.alenasoft.commons.ScoreParser;
import java.util.Arrays;

public class Frame {

  private final ScoreParser scoreParser = ScoreParser.defaultParser();

  private int index;
  private String[] stringPoints;
  private int[] points;
  private int score;

  public Frame(int index, String[] stringPoints) {
    this.index = index;
    this.stringPoints = stringPoints;
    this.score = GameConstants.minPinfall;
    this.points =
        Arrays.stream(this.stringPoints)
            .mapToInt(
                p -> {
                  try {
                    return this.scoreParser.parseToNumericScore(p);
                  } catch (InvalidInputScoreException e) {
                    return GameConstants.errorValue;
                  }
                })
            .filter(p -> p != GameConstants.errorValue)
            .toArray();
  }

  public int[] getPoints() {
    return this.points;
  }

  public String[] getStringPoints() {
    return this.stringPoints;
  }

  public int getIndex() {
    return this.index;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  public int sumOfPoints() {
    return Arrays.stream(this.stringPoints)
        .mapToInt(
            p -> {
              try {
                return this.scoreParser.parseToNumericScore(p);
              } catch (InvalidInputScoreException e) {
                return GameConstants.errorValue;
              }
            })
        .sum();
  }

  public boolean isFirstFrame() {
    return this.index == 1;
  }
}
