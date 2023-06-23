package com.jmg.checkagro.provider.events;


import com.jmg.checkagro.provider.client.CheckMSClient;
import com.jmg.checkagro.provider.config.RabbitMQConfig;
import com.jmg.checkagro.provider.model.Provider;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CrearProviderEvent {
    private final RabbitTemplate rabbitTemplate;


    public CrearProviderEvent(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCrearProvider(Provider provider) {
        CheckMSClient.DocumentRequest documentRequest = CheckMSClient.DocumentRequest.builder()
                .documentType(provider.getDocumentType())
                .documentValue(provider.getDocumentNumber())
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NAME, documentRequest);
    }

}
