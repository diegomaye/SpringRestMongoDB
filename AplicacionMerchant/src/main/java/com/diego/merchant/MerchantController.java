package com.diego.merchant;

import com.diego.merchant.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*
        ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantRepository repository;

    @RequestMapping(value = "/{merchant_id}", method = RequestMethod.GET)
    public ResponseEntity<?>  getMerchantByMerchantId(@PathVariable("merchant_id") String merchant_id) {
        if(repository.findBy_merchant_id(merchant_id)!=null)
            return new ResponseEntity<>("El merchant existe", HttpStatus.OK);
        return new ResponseEntity<>("El merchant no existe", HttpStatus.NOT_FOUND);
    }
}
