package com.alenasoft;

import com.alenasoft.exceptions.FrameNotExistsException;
import com.alenasoft.exceptions.InvalidInputScoreException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface FrameOrganizer {

  static List<Frame> organizeScores(List<String> inputPoints)
      throws InvalidInputScoreException {
    Iterator<String> iterator = inputPoints.iterator();
    List<Frame> frames = new ArrayList<>();
    int index = 1;

    while (iterator.hasNext()) {
      String currentInput = iterator.next();
      int currentValue = ScoreParser.parseToNumericScore(currentInput);

      if (currentValue == 10 && index != 10) {
        String[] points = { currentInput };
        frames.add(new Frame(index, points));
        index++;
        continue;
      }

      if (index != 10) {
        String[] points = {currentInput, iterator.next()};
        frames.add(new Frame(index, points));
        index++;
      } else {
        String[] points = {currentInput, iterator.next(), iterator.next()};
        frames.add(new Frame(index, points));
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
