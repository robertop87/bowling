package com.alenasoft.application.strategies;

import static org.junit.Assert.assertEquals;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.ScoreStrategy;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StrikeScoreStrategyTest {

  private FrameOrganizer frameOrganizer;

  @Before
  public void setUp() {
    this.frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  }

  @Test
  public void testStrikeScoreForFirstFrame() throws InvalidInputScoreException {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(2, new String[] { "7", "3" }),
        new Frame(3, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    Assert.assertEquals(20, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFourthFrameJeffCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 4;
    Frame previousFrame = new Frame(3, new String[] { "9", "0" });
    previousFrame.setScore(48);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(5, new String[] { "0", "8" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(66, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForEighthFrameJeffCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 8;
    Frame previousFrame = new Frame(7, new String[] { "0", "6" });
    previousFrame.setScore(90);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(9, new String[] { "10" }),
        new Frame(10, new String[] { "10", "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(120, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForNinethFrameJeffCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 9;
    Frame previousFrame = new Frame(8, new String[] { "10" });
    previousFrame.setScore(120);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(10, new String[] { "10", "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(148, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForThirdFrameJohnCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 3;
    Frame previousFrame = new Frame(2, new String[] { "6", "3" });
    previousFrame.setScore(25);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(4, new String[] { "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(44, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFifthFrameJohnCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 5;
    Frame previousFrame = new Frame(4, new String[] { "8", "1" });
    previousFrame.setScore(53);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(6, new String[] { "10" }),
        new Frame(7, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(82, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSixthFrameJohnCase()
      throws InvalidInputScoreException {
    int targetFrameIndex = 6;
    Frame previousFrame = new Frame(5, new String[] { "10" });
    previousFrame.setScore(82);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(7, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(101, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFirstFrameOnPerfectGame()
      throws InvalidInputScoreException {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(2, new String[] { "10" }),
        new Frame(3, new String[] { "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(30, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFifthFrameOnPerfect()
      throws InvalidInputScoreException {
    int targetFrameIndex = 5;
    Frame previousFrame = new Frame(4, new String[] { "10" });
    previousFrame.setScore(120);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(6, new String[] { "10" }),
        new Frame(7, new String[] { "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(150, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForNinethFrameOnPerfect()
      throws InvalidInputScoreException {
    int targetFrameIndex = 9;
    Frame previousFrame = new Frame(8, new String[] { "10" });
    previousFrame.setScore(240);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(10, new String[] { "10", "10", "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(270, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }
}
