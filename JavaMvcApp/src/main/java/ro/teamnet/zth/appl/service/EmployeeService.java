package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by Aimandis on 7/15/2016.
 */
public interface EmployeeService {
    public List<Employee> findAllEmployees();
    public Employee findOneEmployee(Long id);
    public void deleteOneEmployee(Long id);
}
