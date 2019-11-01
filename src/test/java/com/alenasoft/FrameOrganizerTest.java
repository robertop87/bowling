package com.alenasoft;

import static org.junit.Assert.assertEquals;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FrameOrganizerTest {

  private FrameOrganizer frameOrganizer;

  @Before
  public void setUp() {
    this.frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  }

  @Test
  public void testOrganizeScoresForJeffCase()
      throws InvalidInputScoreException {
    // Jeff player input data taken from Requirements Document
    List<String> inputPoints =
        Arrays.asList("10", "7", "3", "9", "0",
            "10", "0", "8", "8", "2", "F", "6",
            "10", "10", "10", "8", "1");

    final List<Frame> frames = this.frameOrganizer.organize(inputPoints);

    // Maximum number of frames should be 10
    assertEquals(10, frames.size());

    //Latest Frame should have 3 items in points
    assertEquals(3, frames.get(frames.size() - 1).getPoints().length);

    // Frames 1, 4, 8 & 9 should have only one point
    assertEquals(1, frames.get(1 - 1).getPoints().length);
    assertEquals(1, frames.get(4 - 1).getPoints().length);
    assertEquals(1, frames.get(8 - 1).getPoints().length);
    assertEquals(1, frames.get(9 - 1).getPoints().length);
  }

  @Test
  public void testOrganizeScoresForJohnCase()
      throws InvalidInputScoreException {
    // John player input data taken from Requirements Document
    List<String> inputPoints =
        Arrays.asList("3", "7", "6", "3", "10", "8", "1", "10", "10", "9", "0", "7", "3", "4", "4", "10", "9", "0");

    final List<Frame> frames = this.frameOrganizer.organize(inputPoints);

    // Maximum number of frames should be 10
    assertEquals(10, frames.size());

    //Latest Frame should have 3 items in points
    assertEquals(3, frames.get(frames.size() - 1).getPoints().length);

    // Frames 3, 5 &  6 should have only one point
    assertEquals(1, frames.get(3 - 1).getPoints().length);
    assertEquals(1, frames.get(5 - 1).getPoints().length);
    assertEquals(1, frames.get(6 - 1).getPoints().length);
  }

  @Test
  public void testOrganizeScoresForPerfectGameCarlCase()
      throws InvalidInputScoreException {
    // John player input data taken from Requirements Document
    List<String> inputPoints =
        Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10");

    final List<Frame> frames = this.frameOrganizer.organize(inputPoints);

    // Maximum number of frames should be 10
    assertEquals(10, frames.size());

    //Latest Frame should have 3 items in points
    assertEquals(3, frames.get(frames.size() - 1).getPoints().length);

    // All Frames should have only one point, but the latest
    for (int i = 0; i < 9; i++) {
      assertEquals(1, frames.get(i).getPoints().length);
    }
  }
}
