package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataModel {

    private String transactionId;
    private String make;
    private String model;
    private String year;
    private String saleTimestamp;
    private String dealerId;
    private String dealerName;
    private String state;
    private String price;

}
