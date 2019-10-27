package com.alenasoft;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreParserTest {

  @Test
  public void testValidScoreToNumericValue() {
    final String testValue = "5";

    assertEquals(5, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testInputScoreWithSpacesToNumericValue() {
    final String testValue = " 5 ";

    assertEquals(5, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testEmptyInputScoreShouldReturnZero() {
    final String testValue = "";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testNullInputScoreShouldReturnZero() {
    final String testValue = null;

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testInvalidInputScoreShouldReturnZero() {
    final String testValue = "invalid";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testFupperCaseInputScoreShouldReturnZero() {
    final String testValue = "F";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testFlowerCaseInputScoreShouldReturnZero() {
    final String testValue = "f";

    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testAllValidNumberInputScores() {
    for (int i = 0; i < 11; i++) {
      final String testValue = Integer.toString(i);
      assertEquals(i, ScoreParser.parseToNumericScore(testValue));
    }
  }

  @Test
  public void testMinBorderCases() {
    String testValue = "0";
    assertEquals(0, ScoreParser.parseToNumericScore(testValue));

    testValue = "-1";
    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }

  @Test
  public void testMaxBorderCases() {
    String testValue = "10";
    assertEquals(10, ScoreParser.parseToNumericScore(testValue));

    testValue = "11";
    assertEquals(0, ScoreParser.parseToNumericScore(testValue));
  }
}
