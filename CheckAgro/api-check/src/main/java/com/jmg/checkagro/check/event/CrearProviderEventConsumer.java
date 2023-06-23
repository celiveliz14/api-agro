package com.jmg.checkagro.check.event;

import com.jmg.checkagro.check.config.RabbitMQConfig;
import com.jmg.checkagro.check.controller.request.DocumentRequest;
import com.jmg.checkagro.check.service.CheckService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CrearProviderEventConsumer {

    private final CheckService checkService;

    public CrearProviderEventConsumer(CheckService checkService) {
        this.checkService = checkService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(DocumentRequest documentRequest) {
        checkService.registerProvider(documentRequest.getDocumentType(), documentRequest.getDocumentValue());
    }
}
