package com.clemble.casino.server.bonus.listener;

import com.clemble.casino.error.ClembleErrorCode;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.bonus.ClientErrorBonusPaymentSource;
import com.clemble.casino.server.bonus.BonusPaymentTransaction;
import com.clemble.casino.server.bonus.BonusService;
import com.clemble.casino.server.event.log.SystemClientErrorEvent;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.validation.Valid;

/**
 * Created by mavarazy on 5/15/15.
 */
public class ClientErrorBonusEventListener implements BonusEventListener<SystemClientErrorEvent> {

    final private Money amount;
    final private BonusService bonusService;

    public ClientErrorBonusEventListener(Money amount, BonusService bonusService) {
        this.amount = amount;
        this.bonusService = bonusService;
    }

    @Override
    public void onEvent(@Valid SystemClientErrorEvent event) {
        // Step 1. Sanity check
        if (event == null)
            return;
        // Step 2. Generating unique bonus marker for the day
        ClientErrorBonusPaymentSource paymentSource = new ClientErrorBonusPaymentSource(event.getError().getCode(), DateTime.now(DateTimeZone.UTC));
        // Step 3. Processing bonus in bonusService
        bonusService.process(new BonusPaymentTransaction(event.getPlayer(), paymentSource, amount));
    }

    @Override
    public String getChannel() {
        return SystemClientErrorEvent.CHANNEL;
    }

    @Override
    public String getQueueName() {
        return SystemClientErrorEvent.CHANNEL + " > bonus";
    }
}
