package com.diego.merchant.repositories;

import com.diego.merchant.models.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    @Query("{ 'merchant_id' : ?0 }")
    Merchant findBy_merchant_id(String merchant_id);
}
