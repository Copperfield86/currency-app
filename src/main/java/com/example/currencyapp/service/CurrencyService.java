package com.example.currencyapp.service;

import com.example.currencyapp.model.CurrencyRequest;
import com.example.currencyapp.repository.CurrencyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRequestRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Double getCurrentCurrencyValue(String currency) {
        String url = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
        var response = restTemplate.getForObject(url, List.class);

        if (response == null || response.isEmpty()) {
            throw new IllegalArgumentException("Invalid response from NBP API");
        }

        var rates = (List<Map<String, Object>>) ((Map<String, Object>) response.get(0)).get("rates");
        return rates.stream()
                .filter(rate -> rate.get("code").equals(currency))
                .map(rate -> (Double) rate.get("mid"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Currency not found"));
    }

    public void saveRequest(String currency, String name, Double value) {
        CurrencyRequest request = new CurrencyRequest(null, currency, name, LocalDateTime.now(), value);
        repository.save(request);
    }

    public List<CurrencyRequest> getAllRequests() {
        return repository.findAll();
    }
}