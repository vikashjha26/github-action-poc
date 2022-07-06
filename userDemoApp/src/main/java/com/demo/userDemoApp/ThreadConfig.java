package com.demo.userDemoApp;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {

	@Value("${threadConfig.asynchronousTask.corePoolSize:20}")
	private int corePoolSize;
	
	@Value("${threadConfig.asynchronousTask.maxPoolSize:50}")
	private int maxPoolSize;
	
	@Value("${threadConfig.asynchronousTask.queueCapacity:5000}")
	private int queueCapacity;
	
	@Bean(name = "asynchronousTask")
	public Executor getAsyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(corePoolSize);
	    executor.setQueueCapacity(queueCapacity);
	    executor.setMaxPoolSize(maxPoolSize);
	    executor.setThreadNamePrefix("threadPoolExecutor-");
	    executor.initialize();
	    return executor;
	}
}

