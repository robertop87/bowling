package com.alenasoft;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FrameOrganizerTest {

  @Test
  public void testOrganizeScores() {
    // Jeff player input data taken from Requirements Document
    List<Integer> points =
        Arrays.asList(10, 7, 3, 9, 0,
            10, 0, 8, 8, 2, 0, 6,
            10, 10, 10, 8, 1);

    final List<Frame> frames = FrameOrganizer.organizeScores(points);

    // Maximum number of frames should be 10
    assertEquals(10, frames.size());

    //Latest Frame should have 3 items in points
    assertEquals(3, frames.get(frames.size() - 1).getPoints().length);

    // Frames 1, 4, 8 & 9 should have only one point
    assertEquals(1, frames.get(1 - 1).getPoints().length);
    assertEquals(1, frames.get(4 - 1).getPoints().length);
    assertEquals(1, frames.get(8 - 1).getPoints().length);
    assertEquals(1, frames.get(9 - 1).getPoints().length);

    System.out.println(FrameOrganizer.organizeScores(points));
  }
}
