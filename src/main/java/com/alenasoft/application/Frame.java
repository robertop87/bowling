package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.ScoreParser;
import java.util.Arrays;

public class Frame {

  private int index;
  private String[] stringPoints;
  private int[] points;
  private int score;

  public Frame(int index, String[] stringPoints) {
    this.index = index;
    this.stringPoints = stringPoints;
    this.score = 0;
    this.points = Arrays.stream(this.stringPoints)
        .mapToInt(p -> {try {
          return ScoreParser.parseToNumericScore(p);
          } catch (InvalidInputScoreException e) {
          return -1;
        }}).filter(p -> p != -1)
        .toArray();
  }

  public int[] getPoints() {
    return this.points;
  }

  public int getIndex() {
    return this.index;
  }

  public String pointsToPrint() throws InvalidInputScoreException {
    if (this.stringPoints.length == 1 && ScoreParser.parseToNumericScore(this.stringPoints[0]) == 10) {
      return String.format("%4s", "X");
    }

    if (this.stringPoints.length == 2 && this.sumOfPoints() == 10) {
      return String.format("%2s%2s", this.stringPoints[0], "/");
    }

    String pointsAsString = "";
    for (int i = 0; i < this.stringPoints.length; i++) {
      if (ScoreParser.parseToNumericScore(this.stringPoints[i]) == 10) {
        pointsAsString = pointsAsString.concat(String.format("%2s", "X"));
        continue;
      }

      pointsAsString = pointsAsString.concat(String.format("%2s", this.stringPoints[i]));
    }

    return pointsAsString;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  public int sumOfPoints() {
    return Arrays.stream(this.stringPoints)
        .mapToInt(p -> {
          try {
            return ScoreParser.parseToNumericScore(p);
          } catch (InvalidInputScoreException e) {
            return 0;
          }
        }).sum();
  }
}
