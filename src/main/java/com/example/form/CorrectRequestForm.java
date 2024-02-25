package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CorrectRequestForm {
	
	private String correctLoginId;
	private String correctUserName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date correctRequestDate;
	
	private String requestStaId;
	private Integer correctRequestId;
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date correctDate;

	private String correctStartTime;
	
	private String correctCloseTime;
	
	private String correctRestTime;
	
	private Integer worktimeStaId;
	
	@NotNull(groups = ValidGroup1.class)
	@Size(min = 1, max = 200, groups = ValidGroup2.class)
	private String correctReason;

}
