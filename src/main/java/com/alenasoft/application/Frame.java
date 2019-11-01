package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import com.alenasoft.commons.ScoreParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

  public int getIndex() {
    return this.index;
  }

  public String pointsToPrint() throws InvalidInputScoreException {
    if (this.stringPoints.length == 1
        && this.scoreParser.parseToNumericScore(this.stringPoints[0]) == GameConstants.strikeValue) {
      return String.format(this.index == 1 ? "\t%s" : "\t\t%s", GameConstants.strike);
    }

    if (this.stringPoints.length == 2 && this.sumOfPoints() == GameConstants.maxPinfall) {
      return String.format(this.index == 1 ? "%s\t%s" : "\t%s\t%s", this.stringPoints[0], GameConstants.spare);
    }

    List<String> maskValues = new ArrayList<>();

    for (String stringPoint : this.stringPoints) {
      maskValues.add(this.scoreParser.parseToNumericScore(stringPoint) == GameConstants.maxPinfall ? GameConstants.strike : stringPoint);
    }

    String pointsAsString = maskValues.stream().collect(Collectors.joining("\t"));
    return this.isFirstFrame() ? pointsAsString : "\t".concat(pointsAsString);
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

  private boolean isFirstFrame() {
    return this.index == 1;
  }
}
