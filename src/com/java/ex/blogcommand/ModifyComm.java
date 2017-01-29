package com.java.ex.blogcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.blogdao.BlogDao;


public class ModifyComm implements BlogCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		String pTitle = request.getParameter("pTitle");
		String pContent = request.getParameter("pContent");
		
		BlogDao  dao = new BlogDao();
		dao.modify(pId, pName, pTitle, pContent);
	}
}
