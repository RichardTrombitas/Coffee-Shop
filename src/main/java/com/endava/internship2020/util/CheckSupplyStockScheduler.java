package com.endava.internship2020.util;

import com.endava.internship2020.util.CheckSupplyStockRunnable;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class CheckSupplyStockScheduler {
    @Getter
    private boolean enabled = false;

    private final CheckSupplyStockRunnable checkSupplyStockRunnable;
    private ScheduledExecutorService executorService;

    @Autowired
    public CheckSupplyStockScheduler(CheckSupplyStockRunnable checkSupplyStockRunnable) {
        this.checkSupplyStockRunnable = checkSupplyStockRunnable;
    }

    public void toggleScheduledSupplyStockCheck() {
        enabled = !enabled;
        if (enabled) {
            executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(checkSupplyStockRunnable, 4, 9, TimeUnit.SECONDS);
            System.out.println("\nScheduled supply stock checking enabled.\n");
            return;
        }
        executorService.shutdown();
        System.out.println("\nScheduled supply stock checking disabled.\n");
    }
}
