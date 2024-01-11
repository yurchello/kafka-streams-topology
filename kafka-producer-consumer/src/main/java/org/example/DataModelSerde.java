package org.example;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.serialization.Serdes;
import org.example.model.DataModel;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Setter
@Getter
public class DataModelSerde extends Serdes.WrapperSerde<DataModel> {
    public DataModelSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(DataModel.class));
    }
}