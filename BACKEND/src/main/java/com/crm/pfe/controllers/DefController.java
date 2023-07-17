package com.crm.pfe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefController {
    @GetMapping
    public String sendIt() {
        return "it works";
    }
 }
