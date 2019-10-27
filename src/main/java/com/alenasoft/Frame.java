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
