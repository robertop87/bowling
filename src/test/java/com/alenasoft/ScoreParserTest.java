package com.alenasoft;

import static org.junit.Assert.assertEquals;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.ScoreParser;
import org.junit.Test;

public class ScoreParserTest {

  @Test
  public void testValidScoreToNumericValue() throws InvalidInputScoreException {
    final String testValue = "5";

    assertEquals(5, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testInputScoreWithSpacesToNumericValue()
      throws InvalidInputScoreException {
    final String testValue = " 5 ";

    assertEquals(5, ScoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testEmptyInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testNullInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = null;
    ScoreParser.parseToNumericScore(testValue);
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testInvalidInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "invalid";
    ScoreParser.parseToNumericScore(testValue);
  }

  @Test
  public void testFupperCaseInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "F";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testFlowerCaseInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "f";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testAllValidNumberInputScores()
      throws InvalidInputScoreException {
    for (int i = 0; i < 11; i++) {
      final String testValue = Integer.toString(i);
      assertEquals(i, ScoreParser.parseToNumericScore(testValue));
    }
  }

  @Test
  public void testMinBorderCases() throws InvalidInputScoreException {
    String testValue = "0";
    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testNegativeValueBorderCase() throws InvalidInputScoreException {
    String testValue = "-1";
    ScoreParser.parseToNumericScore(testValue);
  }

  @Test
  public void testMaxBorderCases() throws InvalidInputScoreException {
    String testValue = "10";
    assertEquals(10, ScoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testGreaterThanMaxBorderCases() throws InvalidInputScoreException {
    String testValue = "11";
    ScoreParser.parseToNumericScore(testValue);
  }
}
