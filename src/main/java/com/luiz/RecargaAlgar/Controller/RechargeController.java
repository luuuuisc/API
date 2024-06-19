package com.luiz.RecargaAlgar.Controller;

import com.luiz.RecargaAlgar.Entity.Recharge;
import com.luiz.RecargaAlgar.Service.RechargeService;
import com.luiz.RecargaAlgar.dto.RechargeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recharge")
@Tag(name = "Recharge", description = "Controller for Recharge")
public class RechargeController {
    @Autowired
    private RechargeService rechargeService;

    @PostMapping
    public ResponseEntity<String> rechargePhone(@RequestBody RechargeDTO rechargeDTO) {
        rechargeService.sendRechargeRequest(rechargeDTO);
        return ResponseEntity.ok("Recharge request sent to the queue.");
    }
}



