package com.alenasoft.strategies;

class StrikeScoreStrategy extends AllPinesDownScoreStrategy {

  private static final int strikeNextPointSize = 2;

  public StrikeScoreStrategy() {
    super(strikeNextPointSize);
  }
}
