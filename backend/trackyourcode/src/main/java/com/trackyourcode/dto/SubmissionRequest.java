package com.trackyourcode.dto;

import lombok.Data;

@Data
public class SubmissionRequest {

    private Long taskId;

    private String code;
}