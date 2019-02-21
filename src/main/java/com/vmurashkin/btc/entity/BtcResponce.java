package com.vmurashkin.btc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class BtcResponce {

    @Getter
    @Setter
    @JsonProperty("Realtime Currency Exchange Rate")
    private Btc btc;


}
