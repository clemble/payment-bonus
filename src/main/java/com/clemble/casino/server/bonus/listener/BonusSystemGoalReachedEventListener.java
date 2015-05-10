package com.clemble.casino.server.bonus.listener;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.bonus.GoalReachedBonusPaymentSource;
import com.clemble.casino.server.bonus.BonusPaymentTransaction;
import com.clemble.casino.server.bonus.BonusService;
import com.clemble.casino.server.event.goal.SystemGoalReachedEvent;

import javax.validation.Valid;

/**
 * Created by mavarazy on 5/10/15.
 */
public class BonusSystemGoalReachedEventListener implements BonusEventListener<SystemGoalReachedEvent> {

    final private Money amount;
    final private BonusService bonusService;

    public BonusSystemGoalReachedEventListener(Money amount, BonusService bonusService) {
        this.amount = amount;
        this.bonusService = bonusService;
    }

    @Override
    public void onEvent(@Valid SystemGoalReachedEvent event) {
        // Step 1. Sanity check
        if (event == null)
            return;
        // Step 2. Generating unique bonus marker for the day
        GoalReachedBonusPaymentSource paymentSource = new GoalReachedBonusPaymentSource(event.getGoalKey());
        // Step 3. Processing bonus in bonusService
        bonusService.process(new BonusPaymentTransaction(event.getPlayer(), paymentSource, amount));

    }

    @Override
    public String getChannel() {
        return SystemGoalReachedEvent.CHANNEL;
    }

    @Override
    public String getQueueName() {
        return SystemGoalReachedEvent.CHANNEL + " > payment";
    }
}
