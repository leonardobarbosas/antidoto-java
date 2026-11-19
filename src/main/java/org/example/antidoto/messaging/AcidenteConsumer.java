package org.example.antidoto.messaging;

import org.example.antidoto.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AcidenteConsumer {

    @RabbitListener(queues = RabbitMQConfig.FILA_ACIDENTES)
    public void receberAcidente(String mensagem) {

        System.out.println("====================================");
        System.out.println(">>> RABBITMQ - MENSAGEM RECEBIDA");
        System.out.println(">>> " + mensagem);
        System.out.println(">>> Notificação de acidente processada!");
        System.out.println("====================================");
    }
}
