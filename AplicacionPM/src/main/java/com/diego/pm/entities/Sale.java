package com.diego.pm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class Sale {
    @Id
    private ObjectId _id;

    private String currency;

    @JsonProperty("amount")
    private Double amount_usd;

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDate date;

    private String transaction_id;
    private String merchant_id;
    private String status;

    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

}
