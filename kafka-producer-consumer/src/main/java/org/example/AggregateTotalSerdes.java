package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.example.model.AggregateTotal;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class AggregateTotalSerdes extends Serdes.WrapperSerde<AggregateTotal> {

    public AggregateTotalSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(AggregateTotal.class));
    }
}