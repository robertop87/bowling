package com.alenasoft;

public class StrikeScoreStrategy extends AllPinesDownScoreStrategy {

  private static final int strikeNextPointSize = 2;

  public StrikeScoreStrategy() {
    super(strikeNextPointSize);
  }
}
