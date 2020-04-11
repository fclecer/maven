package com.fc.springmvc.crud.handlers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fc.springmvc.crud.dao.DepartmentDao;
import com.fc.springmvc.crud.dao.EmployeeDao;
import com.fc.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 查询所有员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String,Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	/**
	 * 新增员工页面相关信息查询
	 * @param map
	 * @return
	 */
	//由于使用Restful风格,这里最好指明value和method
	@RequestMapping(value = "/emp",method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	/**
	 * 完成添加员工操作
	 */
	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	public String insert(@Valid Employee employee,BindingResult result,
			Map<String,Object> map) {
		if (result.getErrorCount()>0) {
			System.out.println("出错了");
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField()+":"+error.getDefaultMessage());
			}
			//若验证出错则,转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}
		
		employeeDao.save(employee);
		System.out.println(employee);
		return "redirect:/emps";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	/**
	 * 修改操作,查询指定信息
	 */
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	public String input(@PathVariable("id")Integer id,Map<String,Object> map) {
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		/**
		 * 这里返回的departments在下拉框的位置,如果employee.department.id与departments
		 * 中的id匹配,则自动选中
		 */
		return "input";
	}
	
	/**
	 * 完成修改操作
	 */
	@RequestMapping(value = "/emp",method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	/**
	 * 1. 形参中由于update中接收的形参没有用@ModelAttribute修改时指定特定名称,
	 * 		所以这里的map存储的key必须是类名首字母小写
	 * 2.该方法中为啥使用@RequestParam接收形参呢,在该项目中,只有修改方法是submit的时候
	 * 		会传递参数,这里完全可以来接收
	 * 3. 方法中if条件用来判断是否为修改方法,修改是获取的id不为空
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id",required = false) Integer id,
			Map<String, Object> map) {
		if (id !=null) {
			map.put("employee", employeeDao.get(id));
		}
		
	}
	
	/**
	 * 演示案例,不自动绑定对象中的lastName属性
	 */
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("lastName");
//	}
}























