package com.java.ex.blogcommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.blogdao.BlogDao;
import com.java.ex.blogdto.BlogDto;

public class ShowListComm implements BlogCommand{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		BlogDao dao = new BlogDao();
		ArrayList<BlogDto> dtos = dao.showList();
		request.setAttribute("list",dtos);
	}
}
