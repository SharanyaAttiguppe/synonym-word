package com.AlgoFocus.SynonymAPP.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AlgoFocus.SynonymAPP.DAO.DAOFactory;
import com.AlgoFocus.SynonymAPP.DAO.SynonymDAO;
import com.AlgoFocus.SynonymAPP.DAO.SynonymJavaBean;

public class WordSearchController extends HttpServlet
{
	
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
	 
	 PrintWriter output=resp.getWriter();
	 resp.setContentType("text/html");
	 
	 String word=req.getParameter("word");
	 System.out.println("word--------->"+word);
	 
	 
	 SynonymDAO synonym=DAOFactory.getDAOInstance();
	 SynonymJavaBean result=synonym.wordSearch(word);
	 System.out.println("Result--------->"+result);

	 
	 if(result != null)
	 {   
		 output.print("Successfully fetched the word and its synonyms");
		 output.print("<h1>"+result.getWord()+"</h1>");
		 output.print("<table border=1>");
		 output.print("<tr>");
		 output.print("<th>Synonym</th>");
		 output.print("<th>Post_Content</th>");
		 
		 output.print("</tr>");
		 output.print("<tr>");
		 output.print("<td>"+result.getSynonym()+"</td>");
		 output.print("<td>"+result.getPost_content()+"</td>");
		 output.print("</tr>");
		 output.print("</table>"); 
	 }
	 else
	 {
		 output.print("<h1>Given Word is Not Exist in this application please Enter</h1>");
		 req.getRequestDispatcher("View/WordSynonymInsertion.jsp").include(req, resp);
		 
		 
	 }
}

}
