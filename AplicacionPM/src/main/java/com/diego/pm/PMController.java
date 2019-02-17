package com.diego.pm;

import com.diego.pm.clients.MerchantAPI;
import com.diego.pm.entities.Exchange;
import com.diego.pm.entities.Sale;
import com.diego.pm.entities.SaleStatus;
import com.diego.pm.repositories.ExchangeRepository;
import com.diego.pm.repositories.SaleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/sale")
public class PMController {
    @Autowired
    private SaleRepository repository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String sale(@Valid @RequestBody Sale sale) {
        String merchantId = sale.getMerchant_id();
        boolean existe = MerchantAPI.getInstance().existeMerchant(sale.getMerchant_id());
        if(existe) {
            Sale existSale = repository.findBy_MerchantAndTarnsactionId(sale.getMerchant_id(), sale.getTransaction_id());
            if(isNull(existSale)){
                //Si la moneda es en pesos entonces convierto a dolares (dado que no puedo customizar el base utilizo siempre pesos)
                //IMPORTANTE:Dado que la cuenta free no me permite customizar el base, hago una regla de 3
                //utilizando pesos a euros (ya que es el base por defecto) y de euros a dolares.
                if(sale.getCurrency().equals("UYU")){
                    Exchange exchange = exchangeRepository.findFirstByOrderByDateDesc();
                    Double usAmount = (sale.getAmount_usd()/exchange.getUYU())*exchange.getUSD();
                    sale.setCurrency("USD");
                    sale.setAmount_usd(usAmount);
                }
                sale.set_id(ObjectId.get());
                sale.setStatus(SaleStatus.PENDING.toString());
                sale.setDate(LocalDate.now());
                repository.save(sale);
                return sale.get_id();
            }
            else return existSale.get_id();
        }
        else return String.format("El merchant id %s no existe", merchantId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sale getSaleBySaleId(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }
}
