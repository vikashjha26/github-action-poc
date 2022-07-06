package com.demo.userDemoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ThreadExecute {
	@Autowired
	private ThreadTestService threadTestService;
//	@Override
//	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//		for(int i=0;i<100;i++)
//		{
//			threadTestService.Task1();
//			threadTestService.Task2();
//			threadTestService.Task3();
//			threadTestService.Task4();
//
//		}
//	}

}
