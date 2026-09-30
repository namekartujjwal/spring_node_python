package com.example.first.client;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;

@Component
public class RegistrarSyncJob {
    @Scheduled(cron = "0 */5 * * * *")
    @SchedulerLock(name = "RegistrarSyncJob_syncTask", lockAtLeastFor = "4m", lockAtMostFor = "5m")
    public void syncTask() {
        System.out.print("String non-overlapping sync task...");
        System.out.print("Sync task completed.");
    }
}
