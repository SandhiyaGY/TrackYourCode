package com.trackyourcode.service;

import com.trackyourcode.dto.EvaluationRequest;
import com.trackyourcode.dto.SubmissionRequest;
import com.trackyourcode.entity.Submission;
import com.trackyourcode.entity.Task;
import com.trackyourcode.entity.User;
import com.trackyourcode.repository.SubmissionRepository;
import com.trackyourcode.repository.TaskRepository;
import com.trackyourcode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Submission submitCode(
            SubmissionRequest request,
            String email) {

        User student =
                userRepository.findByEmail(email)
                        .orElseThrow();

        Task task =
                taskRepository.findById(request.getTaskId())
                        .orElseThrow();

        Submission submission =
                Submission.builder()
                        .code(request.getCode())
                        .task(task)
                        .student(student)
                        .submittedAt(LocalDateTime.now())
                        .build();

        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission evaluateSubmission(
            Long id,
            EvaluationRequest request) {

        Submission submission =
                submissionRepository.findById(id)
                        .orElseThrow();

        submission.setMarks(request.getMarks());
        submission.setFeedback(request.getFeedback());

        return submissionRepository.save(submission);
    }
}