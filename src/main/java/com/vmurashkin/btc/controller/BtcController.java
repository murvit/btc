package com.vmurashkin.btc.controller;

import com.vmurashkin.btc.entity.Btc;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping(path = "json", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "get json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Btc> getJson() {
    return ResponseEntity.ok().body(proxyService.getJson());
  }
}
