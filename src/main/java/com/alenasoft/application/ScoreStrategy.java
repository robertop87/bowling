package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.domain.Frame;
import java.util.List;

public interface ScoreStrategy {

  void score(int frameIndex, List<Frame> frames)
      throws InvalidInputScoreException;
}
