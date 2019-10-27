package com.alenasoft;

import com.alenasoft.exceptions.FrameNotExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface FrameOrganizer {

  static List<Frame> organizeScores(List<String> inputPoints) {
    Iterator<String> iterator = inputPoints.iterator();
    List<Frame> frames = new ArrayList<>();
    int index = 1;

    while (iterator.hasNext()) {
      String currentInput = iterator.next();
      int currentValue = ScoreParser.parseToNumericScore(currentInput);

      if (currentValue == 10 && index != 10) {
        int[] points = {currentValue};
        frames.add(new Frame(index, points));
        index++;
        continue;
      }

      if (index != 10) {
        int[] points = {currentValue, ScoreParser.parseToNumericScore(iterator.next())};
        if ("F".equals(currentInput)) {
          frames.add(new Frame(index, points, 0));
        } else {
          frames.add(new Frame(index, points));
        }
        index++;
      } else {
        int[] points = {currentValue, ScoreParser.parseToNumericScore(iterator.next()), ScoreParser.parseToNumericScore(iterator.next())};
        if ("F".equals(currentInput)) {
          frames.add(new Frame(index, points, 0));
        } else {
          frames.add(new Frame(index, points));
        }
        index++;
      }
    }

    return frames;
  }

  static Frame getByIndex(int index, List<Frame> frames) {
    return frames.stream().filter(f -> f.getIndex() == index)
        .findFirst()
        .orElseThrow(FrameNotExistsException::new);
  }
}
