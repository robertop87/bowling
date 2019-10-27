package com.alenasoft;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Frame {

  private int index;
  private int[] points;
  private int score;

  public Frame(int index, int[] points) {
    this.index = index;
    this.points = points;
    this.score = 0;
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

    return Arrays.stream(this.points)
        .mapToObj(p -> (p == 10)
            ? String.format("%2s", "X")
            : String.format("%2d", p))
        .collect(Collectors.joining(""));
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
