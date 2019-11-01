package com.alenasoft.application;

import com.alenasoft.application.defaults.DefaultFrameOrganizer;
import com.alenasoft.application.exceptions.FrameNotExistsException;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.domain.Frame;
import com.alenasoft.domain.PlayerGame;
import java.util.List;

public interface FrameOrganizer {

  List<Frame> organize(PlayerGame playerGame) throws InvalidInputScoreException;

  default Frame getByIndex(int index, List<Frame> frames) {
    return frames
        .stream()
        .filter(f -> f.getIndex() == index)
        .findFirst()
        .orElseThrow(FrameNotExistsException::new);
  }

  static FrameOrganizer defaultFrameOrganizer() {
    return new DefaultFrameOrganizer();
  }
}
