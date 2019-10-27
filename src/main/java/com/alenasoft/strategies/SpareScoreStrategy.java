package com.alenasoft.strategies;

class SpareScoreStrategy extends AllPinesDownScoreStrategy {

  private static final int spareNextPointSize = 1;

  public SpareScoreStrategy() {
    super(spareNextPointSize);
  }
}
