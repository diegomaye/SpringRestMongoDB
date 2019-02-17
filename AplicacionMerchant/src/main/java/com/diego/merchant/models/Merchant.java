package com.diego.merchant.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "merchant")
public class Merchant {
    @Id
    private ObjectId _id;

    private String merchant_id;

    private String description;

    public Merchant(ObjectId _id, String merchant_id, String description) {
        this._id = _id;
        this.merchant_id = merchant_id;
        this.description = description;
    }

    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getMerchant_id() { return merchant_id; }
    public void setMerchant_id(String merchant_id) { this.merchant_id = merchant_id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


}
