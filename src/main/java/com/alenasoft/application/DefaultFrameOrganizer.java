package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import com.alenasoft.domain.Frame;
import com.alenasoft.domain.PlayerGame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultFrameOrganizer implements FrameOrganizer {

  private final ScoreParser scoreParser = ScoreParser.defaultParser();

  @Override
  public List<Frame> organize(PlayerGame playerGame)
      throws InvalidInputScoreException {
    playerGame.setFrames(this.buildFrames(playerGame.getInputScores()));

    return playerGame.getFrames();
  }

  private List<Frame> buildFrames(List<String> inputPoints)
      throws InvalidInputScoreException {
    Iterator<String> iterator = inputPoints.iterator();
    List<Frame> frames = new ArrayList<>();
    int index = 1;

    while (iterator.hasNext()) {
      String currentInput = iterator.next();
      int currentValue = this.scoreParser.parseToNumericScore(currentInput);

      if (currentValue == GameConstants.strikeValue && index != GameConstants.maxFramesLength) {
        String[] points = {currentInput};
        frames.add(new Frame(index, points));
        index++;
        continue;
      }

      if (index != GameConstants.maxFramesLength) {
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
}
