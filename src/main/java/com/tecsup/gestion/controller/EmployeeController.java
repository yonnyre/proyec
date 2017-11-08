package com.tecsup.gestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.model.Department;
import com.tecsup.gestion.model.Employee;
import com.tecsup.gestion.services.EmployeeService;
   
/**
 * Handles requests for the application home page.
 */
@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
		
	@GetMapping("/admin/menu")
	public String menu() {

		return "/admin/menu";
	}
	
	
	@GetMapping("/admin/emp/list")
	public String list(@ModelAttribute("SpringWeb") Employee employee, ModelMap model) {

		try {
			model.addAttribute("employees", employeeService.findAll());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/emp/list";
	}
	
	@GetMapping("/admin/dep/list")
	public String list2(@ModelAttribute("SpringWeb") Department department, ModelMap model) {

		try {
			model.addAttribute("department", employeeService.findAllDep());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/dep/list";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/{employee_id}")
	public ModelAndView home(@PathVariable int employee_id, ModelMap model) {

		ModelAndView modelAndView = null;
		Employee emp = new Employee();
		try {
			emp = employeeService.find(employee_id);
			logger.info(emp.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView = new ModelAndView("home", "command", emp);

		return modelAndView;
	}
	
	@GetMapping("/admin/emp/{action}/{employee_id}")
	public ModelAndView form(@PathVariable String action, @PathVariable int employee_id, ModelMap model) {

		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			Employee emp = employeeService.find(employee_id);
			logger.info(emp.toString());
			modelAndView = new ModelAndView("admin/emp/" + action, "command", emp);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/emp/" + action, "command", new Employee());
		}

		return modelAndView;
	}
	
	
	@GetMapping("/admin/dep/{action}/{department_id}")
	public ModelAndView form2(@PathVariable String action, @PathVariable int department_id, ModelMap model) {

		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			Department dep = employeeService.findDep(department_id);
			logger.info(dep.toString());
			modelAndView = new ModelAndView("admin/dep/" + action, "command", dep);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dep/" + action, "command", new Employee());
		}

		return modelAndView;
	}
	
	@PostMapping("/admin/emp/editsave")
	public ModelAndView editsave(@ModelAttribute("SpringWeb") Employee emp, ModelMap model) {

		
		logger.info("emp = " + emp);
		
		ModelAndView modelAndView = null;

		try {
			employeeService.update(emp.getLogin(), emp.getPassword(), emp.getFirstname(), emp.getLastname(),
					emp.getSalary(), -1);

			modelAndView = new ModelAndView("redirect:/admin/emp/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/emp/list");
		}

		return modelAndView;
	}
	
	@PostMapping("/admin/emp/delete")
	public ModelAndView delete(@ModelAttribute("SpringWeb") Employee emp, ModelMap model) {

		
		logger.info("emp = " + emp);
		
		ModelAndView modelAndView = null;

		try {
			employeeService.delete(emp.getLogin());

			modelAndView = new ModelAndView("redirect:/admin/emp/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/emp/list");
		}

		return modelAndView;
	}
	

	@PostMapping("/admin/dep/editsave")
	public ModelAndView editsave2(@ModelAttribute("SpringWeb") Department dep, ModelMap model) {

		
		logger.info("dep = " + dep);
		
		ModelAndView modelAndView = null;

		try {
			employeeService.updateDep(dep.getName(), dep.getDesc(), dep.getCity());

			modelAndView = new ModelAndView("redirect:/admin/dep/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dep/list");
		}

		return modelAndView;
	}
	
	
	@PostMapping("/admin/dep/delete")
	public ModelAndView delete2(@ModelAttribute("SpringWeb") Department dep, ModelMap model) {

		
		logger.info("dep = " + dep);
		
		ModelAndView modelAndView = null;

		try {
			employeeService.deleteDep(dep.getName());

			modelAndView = new ModelAndView("redirect:/admin/dep/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dep/list");
		}

		return modelAndView;
	}
	
	
	@GetMapping("/admin/emp/createform")
	public ModelAndView createform() {

		Employee emp = new Employee();

		ModelAndView modelAndView = new ModelAndView("admin/emp/createform", "command", emp);

		return modelAndView;
	}
	
	@PostMapping("/admin/emp/create")
	public ModelAndView create(@ModelAttribute("SpringWeb") Employee emp, ModelMap model) {

		
		ModelAndView modelAndView = null;
		
		try {
			employeeService.create(emp.getLogin(), emp.getPassword(), emp.getFirstname(), emp.getLastname(),
					emp.getSalary(), 12);
			logger.info("new Employee login = " + emp.getLogin());
			modelAndView = new ModelAndView("redirect:/admin/emp/list");
		} catch (DAOException e) {
			logger.error(e.getMessage());
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/emp/createform","command", emp);
		}

		return modelAndView;
	}
	
	@GetMapping("/admin/dep/createform")
	public ModelAndView createform2() {

		Department dep = new Department();

		ModelAndView modelAndView = new ModelAndView("admin/dep/createform", "command", dep);

		return modelAndView;
	}
	
	@PostMapping("/admin/dep/create")
	public ModelAndView create2(@ModelAttribute("SpringWeb") Department dep, ModelMap model) {

		
		ModelAndView modelAndView = null;
		
		try {
			employeeService.create(dep.getName(), dep.getDesc(), dep.getCity());
			logger.info("new Department = " + dep.getName());
			modelAndView = new ModelAndView("redirect:/admin/dep/list");
		} catch (DAOException e) {
			logger.error(e.getMessage());
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dep/createform","command", dep);
		}

		return modelAndView;
	}
	

	
}
