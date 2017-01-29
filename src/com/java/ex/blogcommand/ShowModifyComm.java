package com.java.ex.blogcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.blogdao.BlogDao;
import com.java.ex.blogdto.BlogDto;

public class ShowModifyComm implements BlogCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		String pId = request.getParameter("pId");
		BlogDao dao = new BlogDao();
		BlogDto dto = dao.showContent(pId);
		
		request.setAttribute("modify_view",dto);
	}
}
