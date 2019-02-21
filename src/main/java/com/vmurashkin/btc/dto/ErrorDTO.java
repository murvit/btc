package com.vmurashkin.btc.dto;

import lombok.Getter;
import lombok.Setter;

public class ErrorDTO {

  @Getter @Setter private String message;

  public ErrorDTO(String message) {
    this.message = message;
  }
}
