package com.vmurashkin.btc.service;

import com.vmurashkin.btc.entity.Btc;
import com.vmurashkin.btc.entity.BtcResponce;
import com.vmurashkin.btc.entity.Currency;
import com.vmurashkin.btc.exception.NotReceivedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProxyService {

  private Environment environment;

  @Autowired
  public ProxyService(Environment environment) {
    this.environment = environment;
  }

  private String getUrl(Currency currency) {
    return String.format(
        Optional.ofNullable(environment.getProperty("currency.exchange.url"))
            .orElseThrow(() -> new NotReceivedException("Cannot receive URL from properties")),
        currency);
  }

  public Btc getJson(Currency currency) {

    RestTemplate restTemplate = new RestTemplate();
    BtcResponce btcResponce;
    try {
      btcResponce = restTemplate.getForObject(getUrl(currency), BtcResponce.class);
    } catch (Exception e) {
      throw new NotReceivedException(e.getMessage());
    }

    return Optional.ofNullable(btcResponce)
        .orElseThrow(() -> new NotReceivedException("Response is null"))
        .getBtc();
  }
}
