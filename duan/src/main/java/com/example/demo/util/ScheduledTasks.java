package com.example.demo.util;

import com.example.demo.services.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private HoaDonService hoaDonService;

//    @Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "0 0 */4 * * ?")
    public void updateBillStatus() {
        hoaDonService.autoUpdate();
        System.out.println("alo");
    }
}
