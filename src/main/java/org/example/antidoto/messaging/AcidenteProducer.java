
package org.example.antidoto.messaging;

import org.example.antidoto.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AcidenteProducer {

    private final RabbitTemplate rabbitTemplate;

    public AcidenteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarAcidente(Long acidenteId) {

        String mensagem = "Novo acidente registrado. ID: " + acidenteId;

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ACIDENTES,
                RabbitMQConfig.ROUTING_KEY,
                mensagem
        );

        System.out.println(">>> RABBITMQ - MENSAGEM ENVIADA: " + mensagem);
    }
}
