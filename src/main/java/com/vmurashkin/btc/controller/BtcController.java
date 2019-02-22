package com.vmurashkin.btc.controller;

import com.vmurashkin.btc.entity.Btc;
import com.vmurashkin.btc.entity.Currency;
import com.vmurashkin.btc.model.BtcUser;
import com.vmurashkin.btc.service.ProxyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/btc")
@Api(value = "API BTC trade", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BtcController {

  private static final String PING_MESSAGE = "OK";

  private ProxyService proxyService;

  @Autowired
  BtcController(ProxyService proxyService) {
    this.proxyService = proxyService;
  }

  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Перевірка доступності API")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Void> ping() {
    log.info(PING_MESSAGE);
    return ResponseEntity.ok().build();
  }

  @GetMapping(path = "currency/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get currency price")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Btc> getJson(@PathVariable Currency currency) {
    return ResponseEntity.ok().body(proxyService.getJson(currency));
  }

  @GetMapping(path = "user/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get user")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<BtcUser> getUser() {
    return ResponseEntity.ok().body(proxyService.getUser());
  }

}
