package com.revature.repository;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Transactions;

public interface EmployeeRepository {

	public Employee findByName(String username);
	
	public boolean loginUser(String username);
	
	public boolean loginPassword(String username, String password);
	
	public String type(String username);
	
	public int getId(String username);
	
//	public Long checkBalance(String name);
	
	public List<String> allrequests(String username);
	
	public List<Transactions> allrequests2(List<String> allrequests);

	public List<String> pendingrequests(String username);
	
	public List<Transactions> pendingrequests2(List<String> allrequests);
	
	public List<String> acceptedrequests(String username);
	
	public List<Transactions> acceptedrequests2(List<String> allrequests);
	
	public boolean submit(String name, Transactions transaction);
	
	public boolean updateUser(String username, String newname);
	
	public Long count();
}
