package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;



@Service
public class UserApplicationService {
	
	/**ユーザーの有効性のMapを生成する*/
	public Map<String, Integer> getValidationMap(){
		Map<String, Integer > validationMap = new LinkedHashMap <>();
		validationMap.put("有効", 1);
		validationMap.put("無効", 2);
		return validationMap;
	}
	
	
	/**ユーザーの権限Mapを生成する*/
	public Map<String, Integer> getAuthorityMap(){
		Map<String, Integer > authorityMap = new LinkedHashMap <>();
		authorityMap.put("GENERAL", 1);
		authorityMap.put("ADMIN", 2);
		return authorityMap;
	}
	
}
