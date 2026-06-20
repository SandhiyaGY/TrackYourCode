package com.trackyourcode.controller;

import com.trackyourcode.dto.EvaluationRequest;
import com.trackyourcode.dto.SubmissionRequest;
import com.trackyourcode.entity.Submission;
import com.trackyourcode.security.JwtService;
import com.trackyourcode.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;
    private final JwtService jwtService;

    @PostMapping
    public Submission submitCode(
            @RequestBody SubmissionRequest request,
            @RequestHeader("Authorization")
            String header) {

        String token = header.substring(7);

        String email =
                jwtService.extractEmail(token);

        return submissionService
                .submitCode(request, email);
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {

        return submissionService
                .getAllSubmissions();
    }

    @PutMapping("/{id}/evaluate")
    public Submission evaluateSubmission(
            @PathVariable Long id,
            @RequestBody EvaluationRequest request) {

        return submissionService
                .evaluateSubmission(id, request);
    }
}