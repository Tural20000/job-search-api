package com.turalabdullayev.job_search_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.turalabdullayev.job_search_api.model.Job;
import com.turalabdullayev.job_search_api.model.JobType;

import jakarta.persistence.criteria.Predicate;

public class JobSpecification {

	public static Specification<Job> filterJobs(String keyword, String location, JobType jobType) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			// 1. Açar söz axtarışı (Title və ya Description daxilində)
			if (keyword != null && !keyword.isEmpty()) {
				String lowerKeyword = "%" + keyword.toLowerCase() + "%";
				Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), lowerKeyword);
				Predicate descPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
						lowerKeyword);
				predicates.add(criteriaBuilder.or(titlePredicate, descPredicate));
			}

			// 2. Yerə görə filtrləmə
			if (location != null && !location.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("location"), location));
			}

			// 3. İş növünə görə filtrləmə
			if (jobType != null) {
				predicates.add(criteriaBuilder.equal(root.get("jobType"), jobType));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}