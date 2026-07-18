package com.samsung.companyms.company;

import com.samsung.jobbackend.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
