package com.java.ex.blogdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.ex.blogdto.BlogDto;


public class BlogDao {

	DataSource dataSource;
    
	public BlogDao(){
		try { 
			//Connection Pool
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myblog");
		
            System.out.println("connected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("context error");
		}
	}
	
	//리스트보여줌
	public ArrayList<BlogDto> showList(){
		ArrayList<BlogDto> dtos= new ArrayList<BlogDto>();
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet resultSet = null;
		
		try{
			//데이터베이스의 모든 자료 가져옴
			String query = "select pId, pName, pTitle, pContent, pDate from blog_post order by pId desc";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				int pId = resultSet.getInt("pId");
				String pName = resultSet.getString("pName");
				String pTitle = resultSet.getString("pTitle");
				String pContent = resultSet.getString("pContent");
				Timestamp pDate = resultSet.getTimestamp("pDate");
			
				BlogDto dto = new BlogDto(pId, pName, pTitle, pContent, pDate);
				dtos.add(dto);  //결과데이터추가 
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				//다쓴자료들은 반환
				if(resultSet != null)resultSet.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	//글 내용보여줌
	public BlogDto showContent(String strId){
		BlogDto dto = null;
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet resultSet = null;
	
		try{
			//strId에 해당하는 이름, 제목, 내용을 가져옴
			String query = "select * from blog_post where pId = ?";
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1 , Integer.parseInt(strId));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int pId = resultSet.getInt("pId");
				String pName = resultSet.getString("pName");
				String pTitle = resultSet.getString("pTitle");
				String pContent = resultSet.getString("pContent");
				Timestamp pDate = resultSet.getTimestamp("pDate");
				
				//데이터객체에 저장
				dto = new BlogDto(pId, pName, pTitle, pContent, pDate);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(resultSet != null)resultSet.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return dto;
	
	}
	
	//글작성
	public void write(String pName, String pTitle, String pContent){
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try{
			String query = "insert into blog_post (pName, pTitle, pContent) values(?,?,?)";
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pName);
			pstmt.setString(2, pTitle);
			pstmt.setString(3, pContent);
			int rn = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public void modify(String pId, String pName, String pTitle, String pContent){
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try{
			String query = "update blog_post set pName = ?, pTitle = ?, pContent = ? where pId = ?";
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pName);
			pstmt.setString(2, pTitle);
			pstmt.setString(3, pContent);
			pstmt.setInt(4, Integer.parseInt(pId));
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	
	//pId에 해당하는 글 삭제
	public void delete(String pId){
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try{
			String query = "delete from blog_post where pId = ?";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(pId));
			int rn = pstmt.executeUpdate();
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
}
