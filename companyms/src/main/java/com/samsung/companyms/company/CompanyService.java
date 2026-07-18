package com.samsung.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
    boolean updateCompanyById(Long id, Company company);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company findCompanyById(Long id);
}
