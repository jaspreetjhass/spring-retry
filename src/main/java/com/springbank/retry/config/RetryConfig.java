package com.springbank.retry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "retry.config")
public class RetryConfig {

	private Integer maxAttempts;
	private Integer intialInterval;
	private Integer maxInterval;
	private Double multiplier;
	private boolean throwLastExceptionOnExhausted;

	@Bean
	public RetryTemplate retryTemplate() {
		final RetryTemplate retryTemplate = new RetryTemplate();

		final ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(intialInterval);
		backOffPolicy.setMaxInterval(maxInterval);
		backOffPolicy.setMultiplier(multiplier);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxAttempts);
		retryTemplate.setRetryPolicy(retryPolicy);

		retryTemplate.setThrowLastExceptionOnExhausted(throwLastExceptionOnExhausted);
		return retryTemplate;
	}

}
