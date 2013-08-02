package com.mckesson.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mckesson.bo.HelloWorldBo;

@WebService
public class HelloWorldWS {
	
	@Autowired
	HelloWorldBo helloWorldBo;
	
	@WebMethod(operationName="getHelloWorld")
	public String getHelloWorld(String name) {
		return helloWorldBo.getHelloWorld(name);
	}
}
