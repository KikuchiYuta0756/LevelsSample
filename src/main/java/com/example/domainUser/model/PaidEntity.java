package com.example.domainUser.model;

import lombok.Data;

@Data
public class PaidEntity {
	private Integer paidId;
	private Integer userId;
	private Integer paidDays;
}
