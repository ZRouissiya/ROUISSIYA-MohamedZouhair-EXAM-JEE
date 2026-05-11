package com.rouissiya.zouhair.web;

import com.rouissiya.zouhair.dtos.DashboardStatsDTO;
import com.rouissiya.zouhair.services.AssuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardRestController {

    private final AssuranceService assuranceService;

    @GetMapping("/stats")
    public DashboardStatsDTO stats() {
        return assuranceService.dashboardStats();
    }
}