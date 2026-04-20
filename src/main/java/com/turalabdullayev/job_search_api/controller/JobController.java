package com.turalabdullayev.job_search_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turalabdullayev.job_search_api.dto.JobRequest;
import com.turalabdullayev.job_search_api.model.Job;
import com.turalabdullayev.job_search_api.model.JobType;
import com.turalabdullayev.job_search_api.service.JobService;

import jakarta.validation.Valid;
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
			@RequestParam(defaultValue = "created_at") String sortBy, // BURANI DƏYİŞDİK: created_at
			@RequestParam(defaultValue = "desc") String sortDir) {

		// Əgər istifadəçi hələ də köhnə createdAt göndərərsə, onu qorumaq üçün:
		String actualSortBy = sortBy.equals("createdAt") ? "created_at" : sortBy;

		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(actualSortBy).descending()
				: Sort.by(actualSortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Job> jobs = jobService.searchJobs(keyword, location, jobType, pageable);
		return ResponseEntity.ok(jobs);
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping
	public ResponseEntity<Job> createJob(@Valid @RequestBody JobRequest jobRequest) {
		return ResponseEntity.ok(jobService.saveJob(jobRequest));

	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody JobRequest jobRequest) {
		return ResponseEntity.ok(jobService.updateJob(id, jobRequest));
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
		jobService.deleteJob(id);
		return ResponseEntity.noContent().build();

	}

}
