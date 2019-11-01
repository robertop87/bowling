package com.alenasoft.application.strategies;

import static org.junit.Assert.assertEquals;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.ScoreStrategy;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpareScoreStrategyTest {

  private FrameOrganizer frameOrganizer;

  @Before
  public void setUp() {
    this.frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  }

  @Test
  public void testStrikeScoreForFirstFrameJohnCase() {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new String[] { "3", "7" }),
        new Frame(2, new String[] { "6", "3" }),
        new Frame(3, new String[] { "10" }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    Assert.assertEquals(16, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForEighthFrameJohnCase() {
    int targetFrameIndex = 8;
    Frame previousFrame = new Frame(7, new String[] { "9", "0" });
    previousFrame.setScore(110);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "7", "3" }),
        new Frame(9, new String[] { "4", "4" }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(124, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSecondFrameJeffCase() {
    int targetFrameIndex = 2;
    Frame previousFrame = new Frame(1, new String[] { "10" });
    previousFrame.setScore(20);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "7", "3" }),
        new Frame(3, new String[] { "9", "0" }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(39, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSixthFrameJeffCase() {
    int targetFrameIndex = 6;
    Frame previousFrame = new Frame(5, new String[] { "0", "8" });
    previousFrame.setScore(74);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "8", "2" }),
        new Frame(7, new String[] { "0", "6" }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(84, this.frameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }
}
