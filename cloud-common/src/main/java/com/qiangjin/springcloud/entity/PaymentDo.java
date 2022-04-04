package com.qiangjin.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDo implements Serializable {

    private static final long serialVersionUID = 7982749792209255535L;

    private Long id;
    private String serial;
}
