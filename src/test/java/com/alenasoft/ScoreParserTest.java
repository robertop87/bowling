package com.alenasoft;

import static org.junit.Assert.assertEquals;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.ScoreParser;
import com.alenasoft.application.DefaultScoreParser;
import org.junit.Before;
import org.junit.Test;

public class ScoreParserTest {

  private ScoreParser scoreParser;

  @Before
  public void setUp() {
    this.scoreParser = new DefaultScoreParser();
  }

  @Test
  public void testValidScoreToNumericValue() throws InvalidInputScoreException {
    final String testValue = "5";

    assertEquals(5, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testInputScoreWithSpacesToNumericValue()
      throws InvalidInputScoreException {
    final String testValue = " 5 ";

    assertEquals(5, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testEmptyInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "";

    assertEquals(0, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testNullInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = null;
    this.scoreParser.parseToNumericScore(testValue);
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testInvalidInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "invalid";
    this.scoreParser.parseToNumericScore(testValue);
  }

  @Test
  public void testFupperCaseInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "F";

    assertEquals(0, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testFlowerCaseInputScoreShouldReturnZero()
      throws InvalidInputScoreException {
    final String testValue = "f";

    assertEquals(0, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testAllValidNumberInputScores()
      throws InvalidInputScoreException {
    for (int i = 0; i < 11; i++) {
      final String testValue = Integer.toString(i);
      assertEquals(i, this.scoreParser.parseToNumericScore(testValue));
    }
  }

  @Test
  public void testMinBorderCases() throws InvalidInputScoreException {
    String testValue = "0";
    assertEquals(0, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testNegativeValueBorderCase() throws InvalidInputScoreException {
    String testValue = "-1";
    this.scoreParser.parseToNumericScore(testValue);
  }

  @Test
  public void testMaxBorderCases() throws InvalidInputScoreException {
    String testValue = "10";
    assertEquals(10, this.scoreParser.parseToNumericScore(testValue));
  }

  @Test(expected = InvalidInputScoreException.class)
  public void testGreaterThanMaxBorderCases() throws InvalidInputScoreException {
    String testValue = "11";
    this.scoreParser.parseToNumericScore(testValue);
  }
}
