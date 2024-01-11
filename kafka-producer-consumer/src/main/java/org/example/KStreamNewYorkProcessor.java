package org.example;

import org.apache.kafka.streams.kstream.KStream;
import org.example.model.DataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KStreamNewYorkProcessor {

    @Value("${spring.kafka.topic.newyourk}")
    private String newyourktopic;

    public void process(KStream<String, DataModel> stream) {

        //KSTREAM FILTER: Filter the Stream to get New York sales into a new newyork Topic
        stream.filter((key, dataModel) -> {
                    return dataModel != null && dataModel.getState() != null && dataModel.getState().trim()
                            .equalsIgnoreCase("NEW YORK");
                })
                .to(newyourktopic);

    }
}