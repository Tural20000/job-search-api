package com.turalabdullayev.job_search_api.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.turalabdullayev.job_search_api.model.Job;
import com.turalabdullayev.job_search_api.model.JobType;
import com.turalabdullayev.job_search_api.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
	private final JobRepository jobRepository;

	@Override
	public void run(String... args) {
		if (jobRepository.count() == 0) {
			jobRepository.saveAll(List.of(
					Job.builder().title("Java Developer").description("Spring Boot expert needed").location("Baku")
							.jobType(JobType.FULL_TIME).companyName("TechCorp").build(),
					Job.builder().title("Frontend Engineer").description("React and Tailwind CSS").location("Sumqayit")
							.jobType(JobType.REMOTE).companyName("WebDesign").build(),
					Job.builder().title("Backend Architect").description("Java and Microservices").location("Baku")
							.jobType(JobType.CONTRACT).companyName("FinTech").build()));
			System.out.println("Nümunə məlumatlar bazaya əlavə edildi!");
		}
	}

}
