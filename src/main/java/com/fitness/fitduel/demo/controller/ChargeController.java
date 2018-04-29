package com.fitness.fitduel.demo.controller;

import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fitness.fitduel.demo.service.StripeService;

import java.util.Map;

@RestController
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(Map<String, Object> chargeParams)
            throws StripeException {

        Charge charge = paymentsService.charge(chargeParams);
        return new Gson().toJson(charge);
    }

    @RequestMapping("/hello")
    @ResponseBody
    String hello() {
        return "Hello from Heroku!!!!!!";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}