package com.mckesson.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mckesson.bo.HelloWorldBo;

@WebService
public class HelloWorldWS {
	
	@Autowired
	HelloWorldBo helloWorldBo;
	
	@WebMethod(operationName="getHelloWorld")
	public String getHelloWorld(@WebParam(name = "name") String name) {
		return helloWorldBo.getHelloWorld(name);
	}
}
