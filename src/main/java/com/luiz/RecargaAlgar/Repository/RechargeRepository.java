package com.luiz.RecargaAlgar.Repository;

import com.luiz.RecargaAlgar.Entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
        Optional<Recharge> findByPhoneNumber(String phoneNumber);
    }

