package com.smart.building.smart_building_rabbitmq_subscriber.listener;

import com.smart.building.smart_building_rabbitmq_subscriber.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Sink.class)
@RestController
public class MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private Message message = new Message();

    @StreamListener(target = Sink.INPUT)
    public void listenForOrder(Message order) {
        logger.info(" received new order [" + order.toString() + "] ");
        message.setContent(order.getContent());
    }

    @GetMapping
    public Message getItem() {

        return message;
    }
}
