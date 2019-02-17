package com.diego.pm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Exchange implements Serializable {
    @Id
    private ObjectId _id;
    private Double USD;
    private Double UYU;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDate date;
}
