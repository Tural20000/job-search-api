package com.turalabdullayev.job_search_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turalabdullayev.job_search_api.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

	@Query(value = "SELECT * FROM jobs WHERE to_tsvector('english', title || ' ' || description) @@ plainto_tsquery('english', :keyword)", countQuery = "SELECT count(*) FROM jobs WHERE to_tsvector('english', title || ' ' || description) @@ plainto_tsquery('english', :keyword)", nativeQuery = true)
	Page<Job> fullTextSearch(@Param("keyword") String keyword, Pageable pageable);
}