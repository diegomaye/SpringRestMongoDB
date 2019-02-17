package com.diego.pm.repositories;

import com.diego.pm.entities.Sale;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SaleRepository extends MongoRepository<Sale, String> {
    Sale findBy_id(ObjectId _id);
    @Query("{ 'merchant_id' : ?0 , 'transaction_id' : ?1}")
    Sale findBy_MerchantAndTarnsactionId(String merchant_id, String transaction_id);
}

