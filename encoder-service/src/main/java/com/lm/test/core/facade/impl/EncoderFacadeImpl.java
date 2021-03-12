package com.lm.test.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.test.core.facade.Encoder;
import com.lm.test.core.service.EncoderService;

@Service
public class EncoderFacadeImpl implements Encoder {
	@Autowired
	private EncoderService encoder;

	@Override
	public void encode(String text) {
		encoder.encode(text);
	}

}
