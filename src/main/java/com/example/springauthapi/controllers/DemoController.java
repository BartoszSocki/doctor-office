package com.example.springauthapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/demo")
public class DemoController {

    @PreAuthorize("hasAuthority('WRITE')")
    @GetMapping(path = "/write")
    public String write() {
        return "write";
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping(path = "/read")
    public String read() {
        return "read";
    }

    @GetMapping(path = "/all")
    public String all() {
        return "all";
    }
}
