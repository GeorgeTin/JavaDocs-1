package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by Aimandis on 7/15/2016.
 */

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {

        return new EmployeeDao().getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {
        return new EmployeeDao().getEmployeeById(id);
    }

    @Override
    public void deleteOneEmployee(Long id) {
        new EmployeeDao().deleteEmployee(findOneEmployee(id));
    }

    @Override
    public void addEmployee(Employee emp) {
        new EmployeeDao().insertEmployee(emp);
    }
}
