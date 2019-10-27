package com.alenasoft;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Frame {

  private int index;
  private int indexFault;
  private int[] points;
  private int score;

  public Frame(int index, int[] points) {
    this(index, points, -1);
  }

  public Frame(int index, int[] points, int indexFault) {
    this.index = index;
    this.points = points;
    this.score = 0;
    this.indexFault = indexFault;
  }

  public int getIndex() {
    return this.index;
  }

  public int[] getPoints() {
    return this.points;
  }

  public String pointsToPrint() {
    if (this.points.length == 1 && this.points[0] == 10) {
      return String.format("%4s", "X");
    }

    if (this.points.length == 2 && this.sumOfPoints() == 10) {
      return String.format("%2d%2s", this.points[0], "/");
    }

    String pointsAsString = "";
    for (int i = 0; i < this.points.length; i++) {
      if (this.points[i] == 10) {
        pointsAsString = pointsAsString.concat(String.format("%2s", "X"));
        continue;
      }

      if (this.points[i] == 0 && i == indexFault) {
        pointsAsString = pointsAsString.concat(String.format("%2s", "F"));
        continue;
      }

      pointsAsString = pointsAsString.concat(String.format("%2d", this.points[i]));
    }

    return pointsAsString;
  }

  @Override
  public String toString() {
    String formatedPoints = Arrays.stream(this.points)
        .mapToObj(p -> Integer.toString(p))
        .collect(Collectors.joining("|"));

    return String.format("Frame %d. Points: [%s]", this.index, formatedPoints);
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  public int sumOfPoints() {
    return Arrays.stream(this.points).sum();
  }
}
