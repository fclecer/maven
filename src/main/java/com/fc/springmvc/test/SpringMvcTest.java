package com.fc.springmvc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fc.springmvc.crud.dao.EmployeeDao;
import com.fc.springmvc.crud.entities.Employee;

@Controller
public class SpringMvcTest {

	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value = "/testConversionServiceConverer",method = RequestMethod.POST)
	public String testConverter(@RequestParam("employee") Employee employee) {
		System.out.println("Employee:"+employee);
		employeeDao.save(employee);
		return "redirect:/emps";
		//111
	}
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "helloWorld!"+new Date();
	}
	
	/**
	 * 测试ResponseEntity
	 * @throws IOException 
	 */
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/1.txt");
		body = new byte[in.available()];
		in.read(body);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment);filename=1.txt");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	
}




















