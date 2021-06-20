package com.smart.building.smart_building_rabbitmq_publisher.model;

import java.io.Serializable;


public class Message implements Serializable {
    String content;

    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                '}';
    }
}
