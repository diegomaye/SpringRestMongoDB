package com.diego.pm.schedule;

import com.diego.pm.clients.FixerIOAPI;
import com.diego.pm.entities.Exchange;
import com.diego.pm.repositories.ExchangeRepository;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExchangeSchedule {
    @Autowired
    private ExchangeRepository repository;

    @Scheduled(fixedRate = 30000)
    public void requestAndStoreExchangeRate() throws JSONException {
        Exchange exchange = FixerIOAPI.getInstance().getExchangeRates();
        repository.save(exchange);
    }
}
