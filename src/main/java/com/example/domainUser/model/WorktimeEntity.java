package com.example.domainUser.model;

import java.util.Date;

import lombok.Data;

@Data
public class WorktimeEntity {
	private Integer worktimeId;
	private Date dateTime;
	private Integer userId;
	private Date startTime;
	private Date closeTime;
	private Integer workTime;
	private Integer restTime;
	private Integer overTime;
	
}
