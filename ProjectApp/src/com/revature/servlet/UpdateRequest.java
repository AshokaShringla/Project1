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

import com.revature.model.MTransactions;
import com.revature.model.Transactions;
import com.revature.repository.EmployeeRepositoryJdbc;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(name = "UpdateInfoServlet", urlPatterns = { "/UpdateRequest" })
public class UpdateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeRepositoryJdbc employee = new EmployeeRepositoryJdbc();
		
	    PrintWriter out=response.getWriter();
		String id = request.getParameter("id");
		String choice = request.getParameter("Accept/Deny");
				
		HttpSession session=request.getSession(); 
		
		employee.updateStatus(id, choice);
        
    	List<String> allrequests = employee.Mallrequests();
    	List<MTransactions> allrequests2 = employee.Mallrequests2(allrequests);
    	session.setAttribute("AllRequests", allrequests2);
    	
		List<String> pendingrequests = employee.Mpendingrequests();
        List<MTransactions> pendingrequests2 = employee.Mpendingrequests2(pendingrequests);
        session.setAttribute("PendingRequests", pendingrequests2);
        
        List<String> acceptedrequests = employee.Macceptedrequests();
        List<MTransactions> acceptedrequests2 = employee.Macceptedrequests2(acceptedrequests);
        session.setAttribute("AcceptedRequests", acceptedrequests2);
        
        request.getRequestDispatcher("mpendingrequests.jsp").forward(request, response);
	}

}
