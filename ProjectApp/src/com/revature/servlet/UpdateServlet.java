package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repository.EmployeeRepositoryJdbc;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateEmployeeInfo")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeRepositoryJdbc employee = new EmployeeRepositoryJdbc();
	    PrintWriter out=response.getWriter();
		String name = request.getParameter("user");
		
		HttpSession session=request.getSession(); 
		
		String Remployee = (String) session.getAttribute("username");
		
		employee.updateUser(Remployee , name);
		
        session.setAttribute("employee",name);
        session.setAttribute("username", name);
        
        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
	}

}
