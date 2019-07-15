package com.revature.model;

import java.util.List;

public class Employee {
	
	//login, logout
	private String username;
	private String password;
	private List<String> all_requests;
	private List<String> pending_requests;
	private List<String> resolved_requests;
	
	public Employee(String username, String password, List<String> all_requests, List<String> pending_requests,
			List<String> resolved_requests) {
		super();
		this.username = username;
		this.password = password;
		this.all_requests = all_requests;
		this.pending_requests = pending_requests;
		this.resolved_requests = resolved_requests;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAll_requests() {
		return all_requests;
	}

	public void setAll_requests(List<String> all_requests) {
		this.all_requests = all_requests;
	}

	public List<String> getPending_requests() {
		return pending_requests;
	}

	public void setPending_requests(List<String> pending_requests) {
		this.pending_requests = pending_requests;
	}

	public List<String> getResolved_requests() {
		return resolved_requests;
	}

	public void setResolved_requests(List<String> resolved_requests) {
		this.resolved_requests = resolved_requests;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", all_requests=" + all_requests
				+ ", pending_requests=" + pending_requests + ", resolved_requests=" + resolved_requests + "]";
	}
}
