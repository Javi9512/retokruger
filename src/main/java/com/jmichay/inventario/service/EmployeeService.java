package com.jmichay.inventario.service;

import java.util.Date;
import java.util.List;

import com.jmichay.inventario.entity.Employee;

public interface EmployeeService {

    public Employee getEmployeeById(Long id);

    public List<Employee> getAllEmployees();

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee findByIdentification(String identification);

    public Employee updateStateEmployee(Employee employee);

    public List<Employee> findByVaccinated(boolean vaccinated);

    public List<Employee> findByVaccine(String vaccine);

    public List<Employee> findByVaccinationDate(Date date1, Date date2);

}
