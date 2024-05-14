package com.chathuralakshan.employeemanagementsystem.service;

import com.chathuralakshan.employeemanagementsystem.dto.EmployeeDTO;
import com.chathuralakshan.employeemanagementsystem.entity.Employee;
import com.chathuralakshan.employeemanagementsystem.repository.EmployeeRepository;
import com.chathuralakshan.employeemanagementsystem.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO) {

        if(employeeRepository.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATED;
        }else{
            employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepository.existsById(employeeDTO.getEmpID())){
            employeeRepository.save(modelMapper.map(employeeDTO,Employee.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> resLsit= employeeRepository.findAll();
        return modelMapper.map(resLsit,new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    public EmployeeDTO searchEmployee(int empID) {
        if(employeeRepository.existsById(empID)){
            Employee employee=employeeRepository.findById(empID).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(int empID) {
        if(employeeRepository.existsById(empID)){
            employeeRepository.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
