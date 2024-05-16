package com.chathuralakshan.employeemanagementsystem.controller;

import com.chathuralakshan.employeemanagementsystem.dto.EmployeeDTO;
import com.chathuralakshan.employeemanagementsystem.dto.ResponseDTO;
import com.chathuralakshan.employeemanagementsystem.service.EmployeeService;
import com.chathuralakshan.employeemanagementsystem.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res=employeeService.saveEmployee(employeeDTO);
            if(res.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Employee saved successfully");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_DUPLICATED)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee already exists");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res=employeeService.updateEmployee(employeeDTO);
            if(res.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Employee updated successfully");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals(VarList.RSP_NO_DATA_FOUND)){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Employee not found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        try{
            List<EmployeeDTO> employees = employeeService.getAllEmployees();
            responseDTO.setContent(employees);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeeById/{empID}")
    public ResponseEntity getEmployeeById(@PathVariable int empID){
        try{
            EmployeeDTO res=employeeService.searchEmployee(empID);
            if(res!=null) {
                responseDTO.setContent(res);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Employee not found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployeeById/{empID}")
    public ResponseEntity deleteEmployeeById(@PathVariable int empID){
        try{
            String res=employeeService.deleteEmployee(empID);
            if(res.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Employee deleted successfully");
                responseDTO.setContent(empID);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Employee not found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
