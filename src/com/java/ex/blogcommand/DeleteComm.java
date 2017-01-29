package com.java.ex.blogcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.blogdao.BlogDao;

public class DeleteComm implements BlogCommand{
	
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		String pId = request.getParameter("pId");
		BlogDao dao = new BlogDao();
		dao.delete(pId);
	}
}
