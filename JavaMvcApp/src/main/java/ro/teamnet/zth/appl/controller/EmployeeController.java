package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by Aimandis on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    private EmployeeService empService = new EmployeeServiceImpl();
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees(){
        return empService.findAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(name="id")Long id) {

        return empService.findOneEmployee(id);
        //EmployeeDao emplDao = new EmployeeDao();
        //EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        //return null;
    }

    @MyRequestMethod(urlPath = "/delete")
    public Employee deleteOneEmployee(@MyRequestParam(name="id")Long id){
        Employee emp = empService.findOneEmployee(id);
        empService.deleteOneEmployee(id);
        return emp;
    }
}
