package com.anmol.accountingsoftware.controller;

import com.anmol.accountingsoftware.DTO.DashboardResponseDTO;
import com.anmol.accountingsoftware.service.DashboardService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
//@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;

    }

    @GetMapping
    public DashboardResponseDTO getDashboard(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to

    ) {

        return dashboardService.getDashboard(

                from,

                to

        );

    }

}