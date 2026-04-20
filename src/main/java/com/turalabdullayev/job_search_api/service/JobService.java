package com.turalabdullayev.job_search_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.turalabdullayev.job_search_api.dto.JobRequest;
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
		if (keyword != null && !keyword.isEmpty() && location == null && jobType == null) {
			return jobRepository.fullTextSearch(keyword, pageable);
		}

		Specification<Job> spec = JobSpecification.filterJobs(keyword, location, jobType);
		return jobRepository.findAll(spec, pageable);
	}

	public Job saveJob(JobRequest request) {
		Job job = Job.builder().title(request.getTitle()).description(request.getDescription())
				.companyName(request.getCompanyName()).location(request.getLocation()).jobType(request.getJobType())
				.salaryRange(request.getSalaryRange()).build();

		return jobRepository.save(job);
	}

	public void deleteJob(Long id) {
		if (!jobRepository.existsById(id)) {
			throw new RuntimeException("Is elani tapilmadi: " + id);
		}
		jobRepository.deleteById(id);
	}

	public Job updateJob(Long id, JobRequest request) {
		Job existingJob = jobRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Is elani tapilmadi: " + id));

		existingJob.setTitle(request.getTitle());
		existingJob.setDescription(request.getDescription());
		existingJob.setCompanyName(request.getCompanyName());
		existingJob.setLocation(request.getLocation());
		existingJob.setJobType(request.getJobType());
		existingJob.setSalaryRange(request.getSalaryRange());

		return jobRepository.save(existingJob);
	}

}
