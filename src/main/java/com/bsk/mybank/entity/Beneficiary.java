package com.bsk.mybank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Beneficiary {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer beneficiaryId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "accountId")
	private Account beneficiaryAccount;

}
