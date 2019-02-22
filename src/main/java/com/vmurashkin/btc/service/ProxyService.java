package com.vmurashkin.btc.service;

import com.vmurashkin.btc.BtcUserRepository;
import com.vmurashkin.btc.entity.Btc;
import com.vmurashkin.btc.entity.BtcResponce;
import com.vmurashkin.btc.entity.Currency;
import com.vmurashkin.btc.exception.NotReceivedException;
import com.vmurashkin.btc.model.BtcUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class ProxyService {

  private Environment environment;
  private BtcUserRepository btcUserRepository;

  @Autowired
  public ProxyService(Environment environment, BtcUserRepository btcUserRepository) {
    this.environment = environment;
    this.btcUserRepository = btcUserRepository;
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

  public BtcUser getUser() {
    log.info("Start");
    return btcUserRepository.findBtcUserById(1);
  }
}
