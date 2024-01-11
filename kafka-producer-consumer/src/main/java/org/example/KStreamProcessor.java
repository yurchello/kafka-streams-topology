package org.example;

import org.apache.kafka.streams.kstream.KStream;
import org.example.model.DataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KStreamProcessor {

    @Value("${spring.kafka.topic.texassales}")
    private String texassalestopic;

    public void process(KStream<String, DataModel> stream) {

        //KSTREAM FILTER: Filter the Stream to get Texas sales into a new Texas Topic
        stream.filter((key, dataModel) -> {
                    return dataModel != null && dataModel.getState() != null && dataModel.getState().trim()
                            .equalsIgnoreCase("TEXAS");
                })
                .to(texassalestopic);

    }
}