package com.alenasoft.strategies;

import static org.junit.Assert.assertEquals;

import com.alenasoft.Frame;
import com.alenasoft.FrameOrganizer;
import com.alenasoft.ScoreStrategy;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SimpleScoreStrategyTest {

  @Test
  public void testStrikeScoreForThirdFrameJeffCase() {
    int targetFrameIndex = 3;
    Frame previousFrame = new Frame(2, new String[] { "7", "3" });
    previousFrame.setScore(39);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "9", "0" }));

    ScoreStrategy strategy = new SimpleScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(48, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFifthFrameJeffCase() {
    int targetFrameIndex = 5;
    Frame previousFrame = new Frame(4, new String[] { "10" });
    previousFrame.setScore(66);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "0", "8" }));

    ScoreStrategy strategy = new SimpleScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(74, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSeventhFrameJeffCase() {
    int targetFrameIndex = 7;
    Frame previousFrame = new Frame(6, new String[] { "8", "2" });
    previousFrame.setScore(84);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "0", "6" }));

    ScoreStrategy strategy = new SimpleScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(90, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForLatestFrameJeffCase() {
    int targetFrameIndex = 10;
    Frame previousFrame = new Frame(9, new String[] { "10" });
    previousFrame.setScore(148);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10", "8", "1" }));

    ScoreStrategy strategy = new SimpleScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(167, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForLatestFrameJohnCase() {
    int targetFrameIndex = 10;
    Frame previousFrame = new Frame(9, new String[] { "4", "4" });
    previousFrame.setScore(132);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10", "9", "0" }));

    ScoreStrategy strategy = new SimpleScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(151, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }
}
