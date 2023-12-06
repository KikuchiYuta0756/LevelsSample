package com.example.form;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

public class WorkTimeForm {
	@Data
	public class UserCreateForm {
		private LocalDateTime workDate;
		private Integer userId;
		@DateTimeFormat(pattern = "hh:mm")
		private LocalDateTime startTime;
		@DateTimeFormat(pattern = "hh:mm")
		private LocalDateTime closeTime;
	    private Integer workTime;
		private Integer restTime;
		private Integer workFlg;
		private Integer overTime;
	}
	

}
