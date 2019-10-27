package com.alenasoft.application;

import java.util.List;

public interface ScoreStrategy {

  void score(int frameIndex, List<Frame> frames);
}
