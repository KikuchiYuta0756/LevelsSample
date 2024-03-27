package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class PaidEntity {
	private Integer paidId;
	private String loginId;
	private Integer paidDateNum;
	private Date paidLastGrantedDate;
	private Date hire;
}
