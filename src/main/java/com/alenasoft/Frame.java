package com.alenasoft;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Frame {

  private int index;
  private int[] points;

  public Frame(int index, int[] points) {
    this.index = index;
    this.points = points;
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
}
