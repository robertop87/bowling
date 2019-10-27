package com.alenasoft;

import com.alenasoft.exceptions.FrameNotExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface FrameOrganizer {

  static List<Frame> organizeScores(List<Integer> scores) {
    Iterator<Integer> iterator = scores.iterator();
    List<Frame> frames = new ArrayList<>();
    int index = 1;

    while (iterator.hasNext()) {
      int currentValue = iterator.next();

      if (currentValue == 10 && index != 10) {
        int[] points = {currentValue};
        frames.add(new Frame(index, points));
        index++;
        continue;
      }

      if (index != 10) {
        int[] points = {currentValue, iterator.next()};
        frames.add(new Frame(index, points));
        index++;
      } else {
        int[] points = {currentValue, iterator.next(), iterator.next()};
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
