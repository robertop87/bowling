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
      return Constants.minPinfall;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (Constants.faultChar.equals(sanitizedInputScore)) {
      return Constants.minPinfall;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < Constants.minPinfall || score > Constants.maxPinfall) {
        fireInvalidInputException(sanitizedInputScore);
      }
      return score;
    } catch (NumberFormatException exception) {
      fireInvalidInputException(sanitizedInputScore);
    }
    return Constants.minPinfall;
  }

  private void fireInvalidInputException(String inputScore) throws InvalidInputScoreException {
    log.error(String.format(warningTemplate, inputScore));
    System.err.println(String.format(warningTemplate, inputScore));
    throw new InvalidInputScoreException(String.format(warningTemplate, inputScore));
  }
}
