package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Employee;
import com.revature.model.MTransactions;
import com.revature.model.Transactions;
import com.revature.util.ConnectionUtil;

import oracle.net.aso.a;

public class EmployeeRepositoryJdbc implements EmployeeRepository{

	@Override
	public Employee findByName(String name) {
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			int parameterIndex = 0;
			String sql = "SELECT * FROM \"USER\" WHERE U_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  name);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
			Long u_id = result.getLong("U_ID");
			String u_user = result.getString("U_USERNAME");
			String u_pass = result.getString("U_PASSWORD");
			String u_type = result.getString("U_TYPE");
		
			int parameterIndex2 = 0;
			String sql2 = "SELECT * FROM \"TRANSACTION\" WHERE T_USER_ID = ?";
			
			PreparedStatement statement2= connection.prepareStatement(sql2);
			statement2.setLong(++parameterIndex2, u_id);
			
			ResultSet result2 = statement2.executeQuery();
			
			
			List<String> all_transactions = new ArrayList<>();
			List<String> pending_transactions = new ArrayList<>();
			List<String> resolved_transactions = new ArrayList<>();	
			
			while (result2.next()){
				Long not_needed = result2.getLong("T_ID");
				all_transactions.add(result2.getString("T_NAME"));
				Long not_needed2 = result2.getLong("T_USER_ID");
				all_transactions.add(result2.getString("T_STATUS"));
				String status = result2.getString("T_STATUS");
				if(status.equals("PENDING")) {
				pending_transactions.add(result2.getString("T_NAME"));
				pending_transactions.add(result2.getString("T_STATUS"));}
				if(status.equals("ACCEPTED")) {
					resolved_transactions.add(result2.getString("T_NAME"));
					resolved_transactions.add(result2.getString("T_STATUS"));}
				}
			
				return new Employee (
						u_user, u_pass, all_transactions, pending_transactions, resolved_transactions
						);
		}
		}
		
		catch (SQLException e) {
			System.out.println("Could not find user");
		}
		return null;
		
	}
	
	@Override
	public boolean loginUser(String username) {
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			int parameterIndex = 0;
			String sql = "SELECT * FROM \"USER\" WHERE U_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  username);
			
			ResultSet result = statement.executeQuery();
			
			
			if(result.next()){
				return true;
			}
			else {
				
			}
		}
		
		catch (SQLException e) {
		}
		return false;
	}

	@Override
	public boolean loginPassword(String username, String password) {
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			int parameterIndex = 0;
			String sql = "SELECT * FROM \"USER\" WHERE U_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  username);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()){
				if(password.equals(result.getString("U_PASSWORD"))) {
					return true;
				}
				else {
					return false;
				}
		}
			else {
			}
		}
		
		catch (SQLException e) {
		}
		return false;
	}
	
	@Override
	public String type(String name) {
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="select U_TYPE from \"USER\" where U_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, name);
		
			ResultSet result = statement.executeQuery();
			if(result.next()) {
			return result.getString("U_TYPE");
			}
			else {
				return "not";
			}
			
			
	} catch(Exception e) {
		return "no";
	}
	}
	@Override
	public int getId(String username) {
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="select U_ID from \"USER\" where U_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, username);
		
			ResultSet result = statement.executeQuery();
			if(result.next()) {
			return result.getInt("U_ID");
			}
			else {
				return (Integer) null;
			}
		
	}catch(Exception e) {
		return (Integer) null;
	}
	}
	
	@Override
	public boolean submit(String name, Transactions transaction) {
		// TODO Auto-generated method stub
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="INSERT INTO \"TRANSACTION\" VALUES (null, ?, ?, 'PENDING')";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, transaction.getDescription());
			statement.setInt(++parameterIndex, getId(name));
			statement.executeUpdate();
			return true;
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}	
	}
	
	@Override
	public Long count() {
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			Statement statement = connection.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE, 
				    ResultSet.CONCUR_READ_ONLY);
			ResultSet result = statement.executeQuery("SELECT COUNT(*) AS B_ID FROM BANK");
			
			result.last();
			int count = result.getInt("B_ID");
			result.beforeFirst();
			count += 1;
			
			return Long.valueOf(count);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<String> allrequests(String username) {
		// TODO Auto-generated method stub
		
		Employee employee = findByName(username);

		return employee.getAll_requests();
	}
	
	@Override
	public List<Transactions> allrequests2(List<String> allrequests){
		
		List<Transactions> requests = new ArrayList<Transactions>();
		Transactions transaction = null;
		
		for(int i = 0; i < allrequests.size()-1; i=i+2) {
			String a = allrequests.get(i);
			String b = allrequests.get(i+1);
			transaction = new Transactions(a,b);
			requests.add(new Transactions(a,b));
		}
		return requests;
	}
	
	@Override
	public List<String> pendingrequests(String username) {
		// TODO Auto-generated method stub
		
		Employee employee = findByName(username);

		return employee.getPending_requests();
	}
	
	@Override
	public List<Transactions> pendingrequests2(List<String> pendingrequests){
		
		List<Transactions> requests = new ArrayList<Transactions>();
		Transactions transaction = null;
		
		for(int i = 0; i < pendingrequests.size()-1; i=i+2) {
			String a = pendingrequests.get(i);
			String b = pendingrequests.get(i+1);
			transaction = new Transactions(a,b);
			requests.add(new Transactions(a,b));
		}
		return requests;
	}
	
	@Override
	public List<String> acceptedrequests(String username) {
		// TODO Auto-generated method stub
		
		Employee employee = findByName(username);

		return employee.getResolved_requests();
	}
	
	@Override
	public List<Transactions> acceptedrequests2(List<String> acceptedrequests){
		
		List<Transactions> requests = new ArrayList<Transactions>();
		Transactions transaction = null;
		
		for(int i = 0; i < acceptedrequests.size()-1; i=i+2) {
			String a = acceptedrequests.get(i);
			String b = acceptedrequests.get(i+1);
			transaction = new Transactions(a,b);
			requests.add(new Transactions(a,b));
		}
		return requests;
	}

	@Override
	public boolean updateUser(String username, String newname) {

		Integer id = getId(username);
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="update \"USER\" set U_USERNAME = ? where U_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, newname);	
			statement.setInt(++parameterIndex, id);
			
			ResultSet result = statement.executeQuery();
			
			
			if(result.next()){
				return true;
			}
			else {
				
			}
		}
		
		catch (SQLException e) {
			System.out.println("noo");
		}
		return false;
	}

	@Override
	public List<String> Mallrequests() {

		try(Connection connection = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM \"TRANSACTION\"";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			
			List<String> all_transactions = new ArrayList<>();
//			List<String> pending_transactions = new ArrayList<>();
//			List<String> resolved_transactions = new ArrayList<>();
			
			String id = "";
			
			while(result.next()){
				all_transactions.add(String.valueOf((result.getLong("T_ID"))));
				all_transactions.add(result.getString("T_NAME"));
				id = result.getString("T_USER_ID");
				all_transactions.add(result.getString("T_STATUS"));
				
				String sql1 = "SELECT * FROM \"USER\" WHERE U_ID = ?";
				
				PreparedStatement statement1= connection.prepareStatement(sql1);
				statement1.setLong(1, Long.parseLong(id));
					
				ResultSet result1 = statement1.executeQuery();
				
				if(result1.next()) {
				all_transactions.add(result1.getString("U_USERNAME"));
				}
			}
			
			return all_transactions;

		}
		
		catch (SQLException e) {
		}
		return null;
	}

	@Override
	public List<MTransactions> Mallrequests2(List<String> mallrequests) {
		
		List<MTransactions> requests = new ArrayList<MTransactions>();
		MTransactions mtransaction = null;
		
		for(int i = 0; i < mallrequests.size()-3; i=i+4) {
			String a = mallrequests.get(i);
			String b = mallrequests.get(i+1);
			String c = mallrequests.get(i+2);
			String d = mallrequests.get(i+3);
			mtransaction = new MTransactions(a,b,c,d);
			requests.add(new MTransactions(a,b,c,d));
		}
		return requests;
	}

	@Override
	public List<String> Mpendingrequests() {

		try(Connection connection = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM \"TRANSACTION\"";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			
//			List<String> all_transactions = new ArrayList<>();
			List<String> pending_transactions = new ArrayList<>();
//			List<String> resolved_transactions = new ArrayList<>();
			
			String id = "";
			
			while(result.next()){
				if(result.getString("T_STATUS").equals("PENDING")) {
					pending_transactions.add(String.valueOf((result.getLong("T_ID"))));
					pending_transactions.add(result.getString("T_NAME"));
					id = result.getString("T_USER_ID");
					pending_transactions.add(result.getString("T_STATUS"));
					
					String sql1 = "SELECT * FROM \"USER\" WHERE U_ID = ?";
					
					PreparedStatement statement1= connection.prepareStatement(sql1);
					statement1.setLong(1, Long.parseLong(id));
						
					ResultSet result1 = statement1.executeQuery();
					
					if(result1.next()) {
						pending_transactions.add(result1.getString("U_USERNAME"));
					}
				}
			}
			return pending_transactions;

		}
		
		catch (SQLException e) {
		}
		return null;
	}

	@Override
	public List<MTransactions> Mpendingrequests2(List<String> mallrequests) {
		
		if(mallrequests == null) {
			List<MTransactions> requests = new ArrayList<MTransactions>();
			requests.add(new MTransactions("none","none","none","none"));
			return requests;
		}
		
		List<MTransactions> requests = new ArrayList<MTransactions>();
		MTransactions mtransaction = null;
		
		for(int i = 0; i < mallrequests.size()-3; i=i+4) {
			String a = mallrequests.get(i);
			String b = mallrequests.get(i+1);
			String c = mallrequests.get(i+2);
			String d = mallrequests.get(i+3);
			mtransaction = new MTransactions(a,b,c,d);
			requests.add(new MTransactions(a,b,c,d));
		}
		return requests;
	}

	@Override
	public List<String> Macceptedrequests() {
		try(Connection connection = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM \"TRANSACTION\"";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			
//			List<String> all_transactions = new ArrayList<>();
			List<String> pending_transactions = new ArrayList<>();
//			List<String> resolved_transactions = new ArrayList<>();
			
			String id = "";
			
			while(result.next()){
				if(result.getString("T_STATUS").equals("ACCEPTED")) {
					pending_transactions.add(String.valueOf((result.getLong("T_ID"))));
					pending_transactions.add(result.getString("T_NAME"));
					id = result.getString("T_USER_ID");
					pending_transactions.add(result.getString("T_STATUS"));
					
					String sql1 = "SELECT * FROM \"USER\" WHERE U_ID = ?";
					
					PreparedStatement statement1= connection.prepareStatement(sql1);
					statement1.setLong(1, Long.parseLong(id));
						
					ResultSet result1 = statement1.executeQuery();
					
					if(result1.next()) {
						pending_transactions.add(result1.getString("U_USERNAME"));
					}
				}
			}
			return pending_transactions;

		}
		
		catch (SQLException e) {
		}
		return null;
	}

	@Override
	public List<MTransactions> Macceptedrequests2(List<String> mallrequests) {
		if(mallrequests == null) {
			List<MTransactions> requests = new ArrayList<MTransactions>();
			requests.add(new MTransactions("none","none","none","none"));
			return requests;
		}
		
		List<MTransactions> requests = new ArrayList<MTransactions>();
		MTransactions mtransaction = null;
		
		for(int i = 0; i < mallrequests.size()-3; i=i+4) {
			String a = mallrequests.get(i);
			String b = mallrequests.get(i+1);
			String c = mallrequests.get(i+2);
			String d = mallrequests.get(i+3);
			mtransaction = new MTransactions(a,b,c,d);
			requests.add(new MTransactions(a,b,c,d));
		}
		return requests;
	}

	@Override
	public boolean updateStatus(String id, String choice) {
		
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="update \"TRANSACTION\" set T_STATUS = ? where T_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, choice);
			statement.setInt(++parameterIndex, Integer.parseInt(id));
			
			ResultSet result = statement.executeQuery();
			
			
			if(result.next()){
				return true;
			}
			else {
				
			}
		}
		
		catch (SQLException e) {
		}
		return false;
	}

	public static void main(String[] args) {
	EmployeeRepository repository = new EmployeeRepositoryJdbc();
	repository.updateUser("Ashoka", "udon");
	}
	
	public List<String> viewall(){
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			List<String> all = new ArrayList<String>();
			
			String sql = "SELECT * FROM \"USER\"";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
					if(result.getString("U_TYPE").equals("Employee")) {
						String username=result.getString("U_USERNAME");
						all.add(username);
					}
			}
			return all;
			
		} catch(SQLException e) {
			return null;
		}
	}
	
//	List<String> thing = repository.Mallrequests();
//	System.out.println(thing);
//	System.out.println(repository.Mallrequests2(thing));
//	
//	System.out.println(repository.getId("Ashoka"));
//	
//	List<String> no = repository.allrequests("Ashoka");
//	List<Transactions> yes = repository.allrequests2(no);
//	List<String> no2 = repository.pendingrequests("Ashoka");
//	List<Transactions> yes2 = repository.pendingrequests2(no2);
//	List<String> no3 = repository.acceptedrequests("Ashoka");
//	List<Transactions> yes3 = repository.acceptedrequests2(no3);
//	System.out.println(yes);
//	System.out.println(yes2);
//	System.out.println(yes3);
//	
//	repository.submit("Ashoka", new Transactions("80 in bar","PENDING"));
//	
//	}
}