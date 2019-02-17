package com.diego.pm.repositories;

import com.diego.pm.entities.Exchange;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeRepository extends MongoRepository<Exchange, String> {
    Exchange findFirstByOrderByDateDesc();
}

