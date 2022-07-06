package com.demo.userDemoApp;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	UsersRepository userRepo=new UsersRepository();
	@Autowired
	private ThreadTestService threadTestService;
	
	@GetMapping("users/userslist")
	public List<User> getUserslist()
	{
		return userRepo.getUsers();
	}
	
	@PostMapping("users/addUser")
	public User addUser(@RequestBody User newUser)
	{
		userRepo.createUser(newUser);
		return newUser;
	}
	
	@PutMapping(path="users/updateUser", consumes= {"application/json"})
	public List<User> updateUser(@RequestBody User updateUserData)
	{	
		List<User> updatedUsers=userRepo.updateUser(updateUserData);
		return updatedUsers ;
	}
	
	@DeleteMapping("/users/deleteUser/{id}")
	public String deleteUser(@PathVariable int id)
	{
		User u=userRepo.getUserById(id);
		if(u!=null)
		{
			if(userRepo.deleteUser(u))
			return "deleted";
		}
		return "No data of this type presnt in DB";
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
    
   
}
