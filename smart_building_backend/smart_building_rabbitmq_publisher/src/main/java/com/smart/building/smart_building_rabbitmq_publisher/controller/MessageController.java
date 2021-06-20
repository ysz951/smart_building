package com.smart.building.smart_building_rabbitmq_publisher.controller;

import com.smart.building.smart_building_rabbitmq_publisher.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
public class MessageController {
    @Autowired
    private Source source;
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    @PostMapping("/publish")
    public String publishOrder(@RequestBody Message message)
    {
        source.output().send(MessageBuilder.withPayload(message).build());
        log.info(message.toString());
        return "message_published";
    }
}
