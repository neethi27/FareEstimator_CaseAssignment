package com.afkl.assignment.fare.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class to create thread pool task executor
 * @author x087721
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    /**
     * @return executor
     */
    @Bean("asyncPool")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("async-task-");
        executor.initialize();
        return executor;
    }
}
