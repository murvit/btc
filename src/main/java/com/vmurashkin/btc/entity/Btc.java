package com.vmurashkin.btc.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Btc {

    @Getter
    @Setter
    @JsonProperty("1. From_Currency Code")
    private String fromCode;

    @Getter
    @Setter
    @JsonProperty("2. From_Currency Name")
    private String fromName;

    @Getter
    @Setter
    @JsonProperty("3. To_Currency Code")
    private String toCode;

    @Getter
    @Setter
    @JsonProperty("4. To_Currency Name")
    private String toName;

    @Getter
    @Setter
    @JsonProperty("5. Exchange Rate")
    private String rate;

    @Getter
    @Setter
    @JsonProperty("6. Last Refreshed")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refreshTime;

    @Getter
    @Setter
    @JsonProperty("7. Time Zone")
    private String timeZone;
}
