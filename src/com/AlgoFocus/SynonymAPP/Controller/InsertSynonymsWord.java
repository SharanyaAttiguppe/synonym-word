package com.AlgoFocus.SynonymAPP.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AlgoFocus.SynonymAPP.DAO.DAOFactory;
import com.AlgoFocus.SynonymAPP.DAO.SynonymDAO;

public class InsertSynonymsWord extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
		 PrintWriter out=resp.getWriter();
		 resp.setContentType("text/html");
		 
		 String Word = req.getParameter("word");
		 String Synonyms = req.getParameter("synonym");
		 
		 
		 SynonymDAO sdao = DAOFactory.getDAOInstance();
		 boolean res = sdao.Save(Word, Synonyms);
		 
		 if(res == true)
		 {
			 out.print("<h1>Successfully Insertet "+Word+"</h1>");
		 }
		 else
		 {
			 out.print("<h1>Please Try again</h1>");
			 req.getRequestDispatcher("View/WordSynonymInsertion.jsp").include(req, resp);
		 }
		
		
	}
	
}
