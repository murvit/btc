package com.vmurashkin.btc.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "BTCUSER")
public class BtcUser {

    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "address")
    private String address;

}
