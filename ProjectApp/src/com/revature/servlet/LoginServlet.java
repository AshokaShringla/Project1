package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.model.MTransactions;
import com.revature.model.Transactions;
import com.revature.repository.EmployeeRepositoryJdbc;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginAction")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeRepositoryJdbc employee = new EmployeeRepositoryJdbc();
	    PrintWriter out=response.getWriter(); 
		String name = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		if(employee.loginPassword(name, pass)) {
	        HttpSession session=request.getSession(); 
	        if(employee.type(name).equals("Employee")) {
		        session.setAttribute("employee",name);
		        session.setAttribute("username", name);
		        List<String> allrequests = employee.allrequests(name);
		        List<Transactions> allrequests2 = employee.allrequests2(allrequests);
		        session.setAttribute("AllRequests", allrequests2);
		        
		        List<String> pendingrequests = employee.pendingrequests(name);
		        List<Transactions> pendingrequests2 = employee.pendingrequests2(pendingrequests);
		        session.setAttribute("PendingRequests", pendingrequests2);
		        
		        List<String> acceptedrequests = employee.acceptedrequests(name);
		        List<Transactions> acceptedrequests2 = employee.acceptedrequests2(acceptedrequests);
		        session.setAttribute("AcceptedRequests", acceptedrequests2);
		        
	        	request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
	        
	        }
	        else if(employee.type(name).equals("Manager")) {
	        	
	        	session.setAttribute("username", name);
	        	session.setAttribute("manager", name);
	        	List<String> allrequests = employee.Mallrequests();
	        	List<MTransactions> allrequests2 = employee.Mallrequests2(allrequests);
	        	session.setAttribute("AllRequests", allrequests2);
	        	
		        List<String> pendingrequests = employee.Mpendingrequests();
		        List<MTransactions> pendingrequests2 = employee.Mpendingrequests2(pendingrequests);
		        session.setAttribute("PendingRequests", pendingrequests2);
		        
		        List<String> acceptedrequests = employee.Macceptedrequests();
		        List<MTransactions> acceptedrequests2 = employee.Macceptedrequests2(acceptedrequests);
		        session.setAttribute("AcceptedRequests", acceptedrequests2);
		        
		        List<String> all = employee.viewall();
		        session.setAttribute("all", all);
		        
	        	request.getRequestDispatcher("managerProfile.jsp").forward(request,response);
	        }
	        else {
	        	out.print("Sorry, Permissions have not been set for your account!");
	        }
		}
		else {
			out.print("Sorry, username or password error!");
			response.sendRedirect("index.jsp");
		}
	}

}
