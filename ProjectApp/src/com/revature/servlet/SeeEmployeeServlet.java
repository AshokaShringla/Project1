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
@WebServlet("/CheckEmployee")
public class SeeEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeEmployeeServlet() {
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
		
		if(employee.loginUser(name)) {
	        HttpSession session=request.getSession(); 
	        if(employee.type(name).equals("Employee")) {
		      
		        session.setAttribute("Checked", name);
		        
		        List<String> allrequests = employee.allrequests(name);
		        List<Transactions> allrequests2 = employee.allrequests2(allrequests);
		        session.setAttribute("CheckedRequests", allrequests2);
		        
	        	request.getRequestDispatcher("SeeEmployee.jsp").forward(request, response);
	        
	        }
	        else if(employee.type(name).equals("Manager")) {
	        
	        
	        }
	        else {
	        	out.print("Sorry, Permissions have not been set for your account!");
	        }
		}
		else {
			out.print("Sorry, username error");
			response.sendRedirect("/Logout");
		}
	}

}
