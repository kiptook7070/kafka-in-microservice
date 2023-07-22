package com.eclectics.io.kafkaservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaListeners {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "departments", groupId = "groupId")
    void listener(String data) {
        try {
            LOGGER.info("INITIAL DATA " + data);

            Gson gson = new Gson();
            gson.toJson(data);
            LOGGER.info("JSON ARRAY" + gson);

            ObjectMapper mapper = new ObjectMapper();
            String jsonObject = mapper.writeValueAsString(data);
            LOGGER.info("jsonObject" + jsonObject);

            final StreamsBuilder builder = new StreamsBuilder();
            KStream<String, String> source = builder.stream("departments");
            source.to("streams-pipe-output");
            builder.stream("departments").to("streams-pipe-output");
            final Topology topology = builder.build();
            LOGGER.info("SERDE DATA" + topology.describe());
        } catch (Exception exception){
            LOGGER.info("Caught Error: " + exception.getMessage());
            return;
        }


    }
}
