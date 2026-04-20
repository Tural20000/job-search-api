package com.turalabdullayev.job_search_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turalabdullayev.job_search_api.model.Job;
import com.turalabdullayev.job_search_api.model.JobType;
import com.turalabdullayev.job_search_api.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
	private final JobService jobService;

	@GetMapping
	public ResponseEntity<Page<Job>> getAllJobs(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String location, @RequestParam(required = false) JobType jobType,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "desc") String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Job> jobs = jobService.searchJobs(keyword, location, jobType, pageable);

		return ResponseEntity.ok(jobs);

	}

}
