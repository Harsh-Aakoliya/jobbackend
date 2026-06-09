package com.samsung.jobbackend.company.Impl;

import com.samsung.jobbackend.company.Company;
import com.samsung.jobbackend.company.CompanyRepository;
import com.samsung.jobbackend.company.CompanyService;
import com.samsung.jobbackend.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    private CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company existingCompany = companyOptional.get();
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            existingCompany.setJobs(company.getJobs());
            companyRepository.save(existingCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
