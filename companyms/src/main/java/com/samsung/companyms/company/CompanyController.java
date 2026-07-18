package com.samsung.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;
    private CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    private ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAllCompanies());
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateCompanyById(@PathVariable("id") Long companyId, @RequestBody Company company){
        boolean isUpdated = companyService.updateCompanyById(companyId, company);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("Company updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }

    @PostMapping
    private ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId){
        boolean isDeleted = companyService.deleteCompanyById(companyId);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }

    @GetMapping("/{id}")
    private ResponseEntity<Company> getCompanyById(@PathVariable("id") Long companyId){
        Company company = companyService.findCompanyById(companyId);
        if(company == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

}
