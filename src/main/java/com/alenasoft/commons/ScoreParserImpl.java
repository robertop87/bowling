package com.alenasoft.commons;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreParserImpl implements ScoreParser {

  public static Logger log = LogManager.getLogger();

  @Override
  public int parseToNumericScore(String inputScore)
      throws InvalidInputScoreException {
    if (Objects.isNull(inputScore)) {
      fireInvalidInputException("null");
      return GameConstants.minPinfall;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (GameConstants.fault.equals(sanitizedInputScore)) {
      return GameConstants.minPinfall;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < GameConstants.minPinfall || score > GameConstants.maxPinfall) {
        fireInvalidInputException(sanitizedInputScore);
      }
      return score;
    } catch (NumberFormatException exception) {
      fireInvalidInputException(sanitizedInputScore);
    }
    return GameConstants.minPinfall;
  }

  private void fireInvalidInputException(String inputScore) throws InvalidInputScoreException {
    log.error(String.format(warningTemplate, inputScore));
    throw new InvalidInputScoreException(String.format(warningTemplate, inputScore));
  }
}
