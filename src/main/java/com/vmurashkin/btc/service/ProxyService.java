package com.vmurashkin.btc.service;

import com.vmurashkin.btc.entity.Btc;
import com.vmurashkin.btc.entity.BtcResponce;
import com.vmurashkin.btc.exception.NotReceivedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyService {

  private Environment environment;

  @Autowired
  public ProxyService(Environment environment) {
    this.environment = environment;
  }

  private String getUrl() {
    return environment.getProperty("btc.url");
  }

  public Btc getJson() {

    RestTemplate restTemplate = new RestTemplate();
    BtcResponce btcResponce;
    try {
      btcResponce = restTemplate.getForObject(getUrl(), BtcResponce.class);
      return btcResponce.getBtc();
    } catch (Exception e) {
      throw new NotReceivedException(e.getMessage());
    }
  }
}
