package com.multi.seoulsoul.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cs")
public class CsController {

//	private final CsService csService;
//
//	public CsController(CsService csService) {
//		super();
//		this.csService = csService;
//	}
	
//	public CsController() {
//		super();
//	}
	
	@GetMapping("csMain")
	public void csMain() {

	}
	
	@RequestMapping("qnaInsert")
	public void qnaInsert() {

	}
	
	@GetMapping("qnaAll")
	public void qnaAll() {

	}
}
