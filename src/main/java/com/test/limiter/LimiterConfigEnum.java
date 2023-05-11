package com.test.limiter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum LimiterConfigEnum {

    ORDER(new BigDecimal(2), 20),
    REPORT(new BigDecimal(0.0167), 15);

    // 多少秒恢复一次
    private BigDecimal rate;
    // 桶数量
    private int burst;
}