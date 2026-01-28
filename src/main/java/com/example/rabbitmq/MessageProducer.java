package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class MessageProducer {

    private static final String QUEUE_NAME = "demo-queue";

    public static void main(String[] args) throws Exception {

        Connection connection = RabbitConnectionFactory.createConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(
                QUEUE_NAME,
                false,
                false,
                false,
                null
        );

        String message = "Hello RabbitMQ from java and probably on UI!";

        channel.basicPublish(
                "",
                QUEUE_NAME,
                null,
                message.getBytes()
        );

        System.out.println("Sent: " + message);

        channel.close();
        connection.close();
    }
}
