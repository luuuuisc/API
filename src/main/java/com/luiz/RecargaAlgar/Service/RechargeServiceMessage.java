package com.luiz.RecargaAlgar.Service;
import com.luiz.RecargaAlgar.RabbitMQConfig.RabbitMQConfig;
import com.luiz.RecargaAlgar.dto.RechargeDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RechargeServiceMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRechargeRequest(RechargeDTO recharge) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, recharge);
        System.out.println("Recharge request sent to the queue.");
    }
}