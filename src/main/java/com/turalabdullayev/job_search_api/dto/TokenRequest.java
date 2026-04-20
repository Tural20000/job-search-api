package com.turalabdullayev.job_search_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequest {
	@NotBlank(message = "Refresh token is required")
	private String refreshToken;

}