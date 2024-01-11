package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.example.model.DataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/toyotasales")
public class StreamsController {

    private final StreamsBuilderFactoryBean factoryBean;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.demo}")
    private String sendClientTopic;

    /**
     * READ FROM THE KTABLE DEALER STATE
     **/
    @GetMapping("/dealer/{id}")
    public String getDealerSales(@PathVariable String id) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        //Read the KTable Store and get the total aggregated sales by dealer id
        ReadOnlyKeyValueStore<String, Long> amounts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType("dealer-sales-amount", QueryableStoreTypes.keyValueStore()));
        return "Total Car Sales for Dealer " + id + " is $" + amounts.get(id);
    }

    @PostMapping("/model")
    public String sendOrder(@RequestBody DataModel dataModel) {
        kafkaTemplate.send(sendClientTopic, dataModel.getTransactionId(), dataModel);
        return "OK";
    }

}