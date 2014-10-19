package com.sun00j.dogleg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GameController extends MultiActionController{
	
	public ModelAndView Test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("Test.jsp");
	}

	public void Login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("name");
		String pwd = request.getParameter("password");
		response.setContentType("text/html; charset=GBK");
		request.getSession(true).setAttribute("userName", userName);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userName", userName);
		jsonObj.put("ext", "Welcom");
		response.getWriter().println(jsonObj.toString());
	}
}
