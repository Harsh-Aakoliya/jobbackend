package com.samsung.jobbackend.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);//JPA will run query select * from Review where company_id = ?;
}
