package com.vkopendoh.questerapp.account.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladimir Kopendoh
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/status/check")
    public String status(){
        return "ws-account ==> started";
    }
}
