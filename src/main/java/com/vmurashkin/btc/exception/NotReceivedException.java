package com.vmurashkin.btc.exception;

public class NotReceivedException extends RuntimeException {

  public NotReceivedException(String message) {
    super(message);
  }
}
