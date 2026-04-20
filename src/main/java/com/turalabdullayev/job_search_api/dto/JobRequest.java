package com.turalabdullayev.job_search_api.dto;

import com.turalabdullayev.job_search_api.model.JobType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JobRequest {
	@NotBlank(message = "Basliq bos ola bilmez")
	@Size(min = 3, max = 100, message = "Basliq 3-100 simvol arasi olmalidir")
	private String title;

	@NotBlank(message = "Tesvir bos ola bilmez")
	private String description;

	@NotBlank(message = "Sirket adi bos ola bilmez")
	private String companyName;

	private String location;

	@NotNull(message = "Is novu secilmelidir")
	private JobType jobType;

	private String salaryRange;

}
