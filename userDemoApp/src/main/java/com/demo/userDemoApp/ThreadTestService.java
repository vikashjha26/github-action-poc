package com.demo.userDemoApp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ThreadTestService {
	
	@Async("asynchronousTask")
	public void Task1(){
		System.out.println("Currently Executing Task1 - " + Thread.currentThread().getName());
		int count=0;
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println("count task1="+count);
	}
	
	@Async("asynchronousTask")
	public void Task2(){
		System.out.println("Currently Executing Task2 - " + Thread.currentThread().getName());
		int count=0;
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println("count task2="+count);
	}
	
	@Async("asynchronousTask")
	public void Task3(){
		System.out.println("Currently Executing Task3 - " + Thread.currentThread().getName());
		int count=0;
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println("count task3="+count);
	}
	
	@Async("asynchronousTask")
	public void Task4(){
		System.out.println("Currently Executing Task4 - " + Thread.currentThread().getName());
		int count=0;
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println("count task4="+count);
	}
	
	
    public void whenCorePoolSizeFiveAndMaxPoolSizeTen_thenFiveThreads() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.afterPropertiesSet();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        this.startThreads(taskExecutor, countDownLatch, 10);

        while (countDownLatch.getCount() > 0) {
        	assertEquals(5, taskExecutor.getPoolSize());
        }
    }
    
    void startThreads(ThreadPoolTaskExecutor taskExecutor, CountDownLatch countDownLatch, int numThreads) {
        for (int i = 0; i < numThreads; i++) {
            taskExecutor.execute(() -> {
                try {
                    Thread.sleep(100L * ThreadLocalRandom.current().nextLong(1, 10));
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
    
     void assertEquals(int setcCorePoolSize, int taskExecutorPoolSize) {
        if (setcCorePoolSize != taskExecutorPoolSize) {
            throw new IllegalStateException("The output does not match the expected output, for input: " + taskExecutorPoolSize);
        }
    }

	

}
