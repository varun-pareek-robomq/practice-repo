package com.example.rabbitmq;

import com.rabbitmq.client.*;

public class MessageConsumer {

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

        System.out.println("Waiting for messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Received: " + message);
        };

        channel.basicConsume(
                QUEUE_NAME,
                true,
                deliverCallback,
                consumerTag -> {}
        );
    }
}
