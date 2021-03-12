package com.lm.test.core.service.Impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.lm.test.core.service.EncoderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EncoderImpl implements EncoderService {
	private ExecutorService executor = Executors.newFixedThreadPool(4);

	@Override
	public void encode(String text) {
		List<Callable<String>> tasks = Stream.of(Codifications.values()).map(code -> new ResponseEncoder(text, code))
				.collect(Collectors.toList());
		try {
			List<Future<String>> result = this.executor.invokeAll(tasks);
			result.stream().forEach(item -> {
				try {
					System.out.println(item.get());
				} catch (InterruptedException e) {
					log.error(e.getMessage(), e);
				} catch (ExecutionException e) {
					log.error(e.getMessage(), e);
				}
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
