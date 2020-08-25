package com.bsk.mybank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bsk.mybank.entity.Account;
import com.bsk.mybank.entity.Beneficiary;
import com.bsk.mybank.entity.User;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	public Optional<List<Beneficiary>> findByUser(User user);

	public Optional<Beneficiary> findByBeneficiaryAccount(Account toAccount); 

}
