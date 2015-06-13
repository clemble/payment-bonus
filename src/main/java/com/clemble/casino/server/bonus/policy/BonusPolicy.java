package com.clemble.casino.server.bonus.policy;

import com.clemble.casino.payment.PaymentTransaction;

public interface BonusPolicy {

    boolean eligible(PaymentTransaction transaction);

}
