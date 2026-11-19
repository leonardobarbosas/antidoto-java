package org.example.antidoto.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_ACIDENTES = "antidoto.acidentes";
    public static final String EXCHANGE_ACIDENTES = "antidoto.exchange";
    public static final String ROUTING_KEY = "acidente.registrado";

    @Bean
    public Queue filaAcidentes() {
        return new Queue(FILA_ACIDENTES, true);
    }

    @Bean
    public DirectExchange exchangeAcidentes() {
        return new DirectExchange(EXCHANGE_ACIDENTES);
    }

    @Bean
    public Binding bindingAcidentes(
            Queue filaAcidentes,
            DirectExchange exchangeAcidentes) {

        return BindingBuilder
                .bind(filaAcidentes)
                .to(exchangeAcidentes)
                .with(ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public CommandLineRunner inicializarRabbitMQ(
            ConnectionFactory connectionFactory,
            Queue filaAcidentes,
            DirectExchange exchangeAcidentes,
            Binding bindingAcidentes) {

        return args -> {
            System.out.println(">>> CONECTANDO AO RABBITMQ");

            RabbitAdmin admin = new RabbitAdmin(connectionFactory);

            admin.declareExchange(exchangeAcidentes);
            admin.declareQueue(filaAcidentes);
            admin.declareBinding(bindingAcidentes);

            System.out.println(">>> FILA RABBITMQ CRIADA COM SUCESSO!");
        };
    }
}
