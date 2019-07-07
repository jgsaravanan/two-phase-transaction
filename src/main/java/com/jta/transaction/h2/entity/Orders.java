package com.jta.transaction.h2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "ORDERS", schema = "TRA")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderDescription;

    @ElementCollection
    @CollectionTable(schema = "TRA", name = "ORDER_ITEM_PRICE")
    @MapKeyColumn(name = "ITEM")
    @Column(name = "PRICE")
    private Map<String,Double> itemPriceMap;

}
