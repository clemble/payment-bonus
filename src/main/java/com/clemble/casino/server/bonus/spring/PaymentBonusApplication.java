package com.clemble.casino.server.bonus.spring;

import com.clemble.casino.server.spring.WebBootSpringConfiguration;
import com.clemble.casino.server.spring.common.ClembleBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mavarazy on 3/31/15.
 */
@Configuration
@Import( {WebBootSpringConfiguration.class, PaymentBonusSpringConfiguration.class})
public class PaymentBonusApplication implements ClembleBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentBonusApplication.class, args);
    }
}
