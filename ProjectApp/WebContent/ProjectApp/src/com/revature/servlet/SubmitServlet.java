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

import com.revature.model.Transactions;
import com.revature.repository.EmployeeRepositoryJdbc;

/**
 * Servlet implementation class SubmitServlet
 */
@WebServlet("/Submit")
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		EmployeeRepositoryJdbc employee = new EmployeeRepositoryJdbc();
	    PrintWriter out = response.getWriter(); 
		String description = request.getParameter("description");
		Transactions transaction = new Transactions(description, "PENDING");
		String uname = (String) session.getAttribute("username");
		employee.submit(uname, transaction);
        List<String> allrequests = employee.allrequests(uname);
        List<Transactions> allrequests2 = employee.allrequests2(allrequests);
        session.setAttribute("AllRequests", allrequests2);
        
        List<String> pendingrequests = employee.pendingrequests(uname);
        List<Transactions> pendingrequests2 = employee.pendingrequests2(pendingrequests);
        session.setAttribute("PendingRequests", pendingrequests2);
        
        List<String> acceptedrequests = employee.acceptedrequests(uname);
        List<Transactions> acceptedrequests2 = employee.acceptedrequests2(acceptedrequests);
        session.setAttribute("AcceptedRequests", acceptedrequests2);
		request.getRequestDispatcher("success.jsp").forward(request, response);
		}
//		else {
//			request.getRequestDispatcher("failure.jsp").forward(request, response);
//		}
	}

