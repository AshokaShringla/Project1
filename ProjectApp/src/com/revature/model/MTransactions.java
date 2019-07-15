package com.revature.model;

public class MTransactions {

	private String id;
	private String description;
	private String status;
	private String name;
	
	
	
	public MTransactions(String id, String description, String status, String name) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.name = name;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "MTransactions [id=" + id + ", description=" + description + ", status=" + status + ", name=" + name
				+ "]";
	}
	
	
	
	
	
}
