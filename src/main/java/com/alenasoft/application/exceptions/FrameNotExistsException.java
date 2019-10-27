package com.alenasoft.application.exceptions;

public class FrameNotExistsException extends RuntimeException {

  public FrameNotExistsException() {
    super("Frame does not exists on current frames list");
  }
}
