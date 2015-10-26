package com.sun00j.dogleg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String sqlStr = "select count(1) from user where user_name = ?";
		int cnt = jdbcTemplate.queryForInt(sqlStr, new Object[] { userName });
		if(cnt > 0){
			response.getWriter().print("fail");
			return;
		}
		sqlStr = "insert into user(user_name,password) values(?,?)";
		cnt = jdbcTemplate.update(
				sqlStr,
				new Object[] { userName, password});
		response.getWriter().print("sucess");
		System.out.println("cnt = "+cnt);
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String sqlStr = "select count(1) from user where user_name = ? and password = ?";
		int cnt = jdbcTemplate.queryForInt(
				sqlStr,
				new Object[] { userName, password});
		System.out.println("cnt = "+cnt+" password = "+password);
		if(cnt>0) {
			response.getWriter().print("sucess");
		} else {
			response.getWriter().print("fail");
		}
	}
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userName = request.getParameter("userName");
		String sqlStr = "select count(1) from user where user_name = ?";
		int cnt = jdbcTemplate.queryForInt(sqlStr, new Object[] { userName });
		if(cnt > 0) {
			response.getWriter().print("yes");
		} else {
			response.getWriter().print("no");
		}
	}
}
