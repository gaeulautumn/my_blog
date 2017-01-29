package com.java.ex.blogfrontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ex.blogcommand.BlogCommand;
import com.java.ex.blogcommand.DeleteComm;
import com.java.ex.blogcommand.ModifyComm;
import com.java.ex.blogcommand.ShowContentComm;
import com.java.ex.blogcommand.ShowListComm;
import com.java.ex.blogcommand.ShowModifyComm;
import com.java.ex.blogcommand.WriteComm;

/**
 * Servlet implementation class BlogFrontController
 */
@WebServlet("*.do")
public class BlogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("EUC-KR");
		String viewPage = null;
		BlogCommand comm = null;
		
		//substring으로 주소얻기
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String path = uri.substring(conPath.length());
		
		if(path.equals("/list.do")){
			comm = new ShowListComm();
			comm.execute(request, response);
			viewPage = "list.jsp";
		}
		else if(path.equals("/write_view.do")){
			viewPage = "write_view.jsp";
		}
		else if(path.equals("/write.do")){
			comm = new WriteComm();
			comm.execute(request, response);
			viewPage = "list.do";
		}
		else if(path.equals("/post_view.do")){
			comm = new ShowContentComm();
			comm.execute(request, response);
			viewPage = "post_view.jsp";
		}
		else if(path.equals("/modify_view.do")){ //수정하기눌렀을때
			comm = new ShowModifyComm();
			comm.execute(request, response);
			viewPage = "modify_view.jsp";
		}
		else if(path.equals("/modify.do")){
			comm = new ModifyComm();
			comm.execute(request, response);
			viewPage = "list.do";
		}
		else if(path.equals("/delete.do")){
			comm = new DeleteComm();
			comm.execute(request, response);
			viewPage = "list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
