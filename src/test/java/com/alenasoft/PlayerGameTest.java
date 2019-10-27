package com.alenasoft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.alenasoft.application.PlayerGame;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class PlayerGameTest {

  @Test
  public void testBuildPlayerGameWithSortedScores() {
    // Jeff player input data taken from Requirements Document
    List<String> inputScores =
        Arrays.asList("10", "7", "3", "9", "0",
            "10", "0", "8", "8", "2", "F", "6",
            "10", "10", "10", "8", "1");

    final PlayerGame playerGame = new PlayerGame("Jeff", inputScores);

    final List<String> scores = playerGame.getInputScores();

    // Position with value F should be 0 as score value
    // F is in 10 indexed position
    assertEquals("F", scores.get(10));

    // 1 is in the latest position
    assertEquals("1", scores.get(scores.size()-1));
  }

  @Test
  public void testIncompleteFramesForPlayerGame() {
    List<String> inputScoresIncompleteGame =
        Arrays.asList("10", "0", "8", "8", "2", "F", "6",
            "10", "10", "10", "8", "1");

    final PlayerGame playerGame = new PlayerGame("Jeff", inputScoresIncompleteGame);
    assertFalse(playerGame.isValid());
  }
}
