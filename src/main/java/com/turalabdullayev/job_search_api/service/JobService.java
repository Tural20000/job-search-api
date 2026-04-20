package com.turalabdullayev.job_search_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.turalabdullayev.job_search_api.model.Job;
import com.turalabdullayev.job_search_api.model.JobType;
import com.turalabdullayev.job_search_api.repository.JobRepository;
import com.turalabdullayev.job_search_api.repository.JobSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

	private final JobRepository jobRepository;

	public Page<Job> searchJobs(String keyword, String location, JobType jobType, Pageable pageable) {
		Specification<Job> spec = JobSpecification.filterJobs(keyword, location, jobType);
		return jobRepository.findAll(spec, pageable);
	}

}
