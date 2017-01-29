package com.java.ex.blogcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BlogCommand {
	void execute(HttpServletRequest request, HttpServletResponse response); 
}
