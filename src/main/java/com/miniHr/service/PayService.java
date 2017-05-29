package com.miniHr.service;

import java.util.Map;

import com.miniHr.entity.PayRequest;

public interface PayService {
	public Map<String,String> pay(PayRequest request) throws Exception;
}
