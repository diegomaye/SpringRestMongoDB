package com.diego.pm.schedule;

import com.diego.pm.entities.Sale;
import com.diego.pm.entities.SaleStatus;
import com.diego.pm.repositories.SaleRepository;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@Transactional
public class SaleStatusSchedule {
    @Autowired
    private SaleRepository repository;

    @Scheduled(fixedRate = 30000)
    public void requestAndStoreExchangeRate() throws JSONException {
        List<Sale> sales = repository.findAll();
        for(Sale sale:sales){
            if(sale.getStatus().equals(SaleStatus.PENDING.toString())){
                Random random = new Random();
                int probability = random.nextInt(10);
                if(probability >= 0 && probability < 7){
                    sale.setStatus(SaleStatus.PAID.toString());
                }
                else{
                    sale.setStatus(SaleStatus.REJECTED.toString());
                }
                repository.save(sale);
            }
        }
    }
}
