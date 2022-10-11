package com.jmichay.inventario.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmichay.inventario.entity.Employee;
import com.jmichay.inventario.entity.Role;
import com.jmichay.inventario.entity.User;
import com.jmichay.inventario.entity.Vaccine;
import com.jmichay.inventario.service.EmployeeService;
import com.jmichay.inventario.service.RoleService;
import com.jmichay.inventario.service.VaccineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
@SecurityRequirement(name = "basicAuth")
@Api(value = "Controlador de empleados", description = "Api Empleado")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private VaccineService vaccineService;

    @ApiOperation(value = "Obtener empleados", response = Iterable.class)

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @ApiOperation(value = "Obtener empleados por identificacion", response = Iterable.class)
    @GetMapping("/{identification}")
    public ResponseEntity<Employee> findByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok(employeeService.findByIdentification(identification));
    }

    @ApiOperation(value = "Crear un nuevo empleado", response = Iterable.class)

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody @Validated Employee employee) {

        Employee employeeS = employeeService.findByIdentification(employee.getIdentification());
        if (employeeS != null) {
            // Retornar un mensaje que ya existe el empleado
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            "Ya existe un empleado con la identificacion " + employee.getIdentification()));
        }

        String username = employee.getName().split(" ")[0].toLowerCase()
                + "." + employee.getLastname().split(" ")[0].toLowerCase()
                + employee.getIdentification().substring(0, 3);

        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 89 + 33);
        }
        Role role = roleService.getRoleByName("EMPLOYEE");
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(role);
        employee.setUser(user);
        employee.setVaccinated(false);
        Employee employeeSaved = employeeService.saveEmployee(employee);
        employeeSaved.getUser().setPassword(password);
        return ResponseEntity.ok(employeeSaved);
    }

    @ApiOperation(value = "Actualizar un empleado", response = Iterable.class)
    @PutMapping("/{identificacion}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String identificacion,
            @RequestBody @Validated Employee employee) {
        Employee employeeSaved = employeeService.findByIdentification(identificacion);
        if (employeeSaved == null) {
            return ResponseEntity.notFound().build();
        }
        employeeSaved.setName(employee.getName());
        employeeSaved.setLastname(employee.getLastname());
        employeeSaved.setEmail(employee.getEmail());
        employeeSaved
                .setBirthdate(employee.getBirthdate() != null ? employee.getBirthdate() : employeeSaved.getBirthdate());
        employeeSaved.setAddress(employee.getAddress() != null ? employee.getAddress() : employeeSaved.getAddress());
        employeeSaved.setMobilePhone(
                employee.getMobilePhone() != null ? employee.getMobilePhone() : employeeSaved.getMobilePhone());

        if (employee.getVaccinated() != null && employee.getVaccinated()) {
            if (employee.getDoses() == 0 || employee.getVaccinationDate() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Debe ingresar los datos de la vacuna"));
            }
            Vaccine vaccine = vaccineService.getVaccineByName(employee.getVaccine().getName());
            if (vaccine == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "La vacuna no existe"));
            }
            employeeSaved.setVaccine(vaccine);
            employeeSaved.setDoses(employee.getDoses());
            employeeSaved.setVaccinationDate(employee.getVaccinationDate());
            employeeSaved.setVaccinated(true);

        }

        return ResponseEntity.ok(employeeService.updateEmployee(employeeSaved));
    }

    @ApiOperation(value = "Eliminar un empleado", response = Iterable.class)
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String identificacion) {
        Employee employeeSaved = employeeService.findByIdentification(identificacion);
        if (employeeSaved == null) {
            return ResponseEntity.notFound().build();
        }
        employeeSaved.setDeleted(true);
        return ResponseEntity.ok(Map.of("deleted", employeeService.updateStateEmployee(employeeSaved).getDeleted()));
    }

    @ApiOperation(value = "Obtener empleados por estado de vacunacion", response = Iterable.class)
    @GetMapping("/vaccinated/{vaccinated}")
    public ResponseEntity<List<Employee>> getAllEmployeesByVaccinated(@PathVariable Boolean vaccinated) {
        return ResponseEntity.ok(employeeService.findByVaccinated(vaccinated));
    }

    @ApiOperation(value = "Obtener empleados por tipo de vacuna", response = Iterable.class)
    @GetMapping("/vaccine/{vaccine}")
    public ResponseEntity<List<Employee>> getAllEmployeesByVaccine(@PathVariable String vaccine) {
        return ResponseEntity.ok(employeeService.findByVaccine(vaccine));
    }

    @ApiOperation(value = "Obtener empleados por un rango de fechas de vacunacion, formato: yyy-MM-dd", response = Iterable.class)
    @GetMapping("/date/{date1}/{date2}")
    public ResponseEntity<List<Employee>> getAllEmployeesByDate(@PathVariable String date1,
            @PathVariable String date2) {
        // Convertir las fechas a Date
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try {
            dateOne = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            dateTwo = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();

        }
        return ResponseEntity.ok(employeeService.findByVaccinationDate(dateOne, dateTwo));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @RequestMapping("/**")
    public ResponseEntity<Map<String, String>> handleExceptions() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Endpoint no encontrado");
        return ResponseEntity.badRequest().body(errors);
    }

}
