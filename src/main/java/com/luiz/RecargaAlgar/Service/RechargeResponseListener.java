package com.luiz.RecargaAlgar.Service;

import com.luiz.RecargaAlgar.Repository.RechargeRepository;
import com.luiz.RecargaAlgar.dto.RechargeResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeResponseListener {

    @Autowired
    private RechargeRepository rechargeRepository;

    @RabbitListener(queues = "responseQueue")
    public void receiveMessage(RechargeResponse response) {
        System.out.println("Received response: " + response);
        rechargeRepository.findByPhoneNumber(response.getPhoneNumber())
                .ifPresent(recharge -> {
                    if (response.isSuccess()) {
                        recharge.setStatus("SUCCESS");
                        System.out.println("Recharge successful for: " + response.getPhoneNumber());
                    } else {
                        recharge.setStatus("FAILED");
                        System.out.println("Recharge failed for: " + response.getPhoneNumber() + " Error: " + response.getErrorMessage());
                    }
                    rechargeRepository.save(recharge);
                });
    }
}

