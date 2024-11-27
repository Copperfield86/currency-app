package com.example.currencyapp.controller;

import com.example.currencyapp.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService service;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<?> getCurrentCurrencyValue(@RequestBody Map<String, String> request) {
        String currency = request.get("currency");
        String name = request.get("name");

        try {
            Double value = service.getCurrentCurrencyValue(currency);
            service.saveRequest(currency, name, value);
            return ResponseEntity.ok(Map.of("value", value));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/requests")
    public List<?> getAllRequests() {
        return service.getAllRequests();
    }
}
