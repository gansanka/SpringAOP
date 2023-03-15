package com.spring.aop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.aop.aspect.AOPExecutionOne;
import com.spring.aop.aspect.AOPExecutionTime;
import com.spring.aop.aspect.AOPExecutionTwo;

@Controller
public class AOPController {

	@GetMapping(value = "/executionTime")
	@AOPExecutionTime
	public ResponseEntity<String> getExecutionTime() {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping(value = "/doExecute")
	@AOPExecutionOne
	@AOPExecutionTwo(execute = true)
	public ResponseEntity<String> doExecute() {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping(value = "/dontExecute")
	@AOPExecutionOne
	public ResponseEntity<String> dontExecute() {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
