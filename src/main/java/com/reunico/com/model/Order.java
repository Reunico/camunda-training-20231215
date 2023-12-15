package com.reunico.com.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name="event_order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonAlias("name.findName")

    private String fullName;
    @JsonAlias("name.title")
    private String title;
    @JsonAlias("random.number")
    private Long number;
    @JsonAlias("finance.amount")
    private BigDecimal amount;
    @JsonAlias("address.countryCode")
    private String countryCode;
    private String description;
    private String contractor;

}
