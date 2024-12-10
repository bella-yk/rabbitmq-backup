package com.kac.rabbitmqbackup.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class MonitoringListener {
    private final Logger logger = LoggerFactory.getLogger(MonitoringListener.class);
    private static final String QUEUE_NAME = "q.weather.metar.kac.apr";

    @RabbitListener(queues = QUEUE_NAME)
    public void processInvoice(String message) {
        try {
            logger.info(message);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
