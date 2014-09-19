package com.sun00j.dogleg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GameController extends MultiActionController{
	
	public ModelAndView Test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("Test.jsp");
	}

}
