package com.lm.test.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lm.test.core.facade.Encoder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EncoderController {

	@Autowired
	private Encoder encoder;

	@GetMapping(value = "/encode/{text}", produces = "application/json")
	public String get(@PathVariable String text) {
		String value = "proceso exitoso";
		try {
			encoder.encode(text);
		} catch (Exception ex) {
			value = "proceso fallido";
			log.error(ex.getMessage(), ex);

		}
		return value;
	}

}