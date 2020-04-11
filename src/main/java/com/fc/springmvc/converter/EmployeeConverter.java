package com.fc.springmvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fc.springmvc.crud.entities.Department;
import com.fc.springmvc.crud.entities.Employee;

/**
 * S:原类型
 * T:目标类型
 * 
 * 	要将自定有的转换器添加到SpringIOC容器中
 * @author Administrator
 *
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		if (source!=null) {
			String[] vals = source.split("-");
			if (vals!=null && vals.length==4) {
				String lastName = vals[0];
				String email = vals[1];
				Integer gender = Integer.parseInt(vals[2]);
				Department department = new Department();
				department.setId(Integer.parseInt(vals[3]));
				Employee employee = new Employee(null, lastName, email, gender, department);
				System.out.println("Converter:"+employee);
				return employee;
			}
		}
		return null;
	}


}
