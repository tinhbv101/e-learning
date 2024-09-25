package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.elearning.dtos.course.request.DecreaseProgressRequest;
import vn.hcmute.elearning.entity.*;
import vn.hcmute.elearning.enums.DisplayStatus;
import vn.hcmute.elearning.enums.ProgressType;
import vn.hcmute.elearning.repository.*;
import vn.hcmute.elearning.service.interfaces.IProgressService;

@Service
@RequiredArgsConstructor
public class ProgressService implements IProgressService {
    private final ExamProgressRepository examProgressRepository;
    private final DocumentProgressRepository documentProgressRepository;
    private final DocumentRepository documentRepository;
    private final ExamRepository examRepository;

    @Override
    public DocumentProgress increaseDocumentProgress(Document document, User user) {
      DocumentProgress documentProgress = new DocumentProgress();
      documentProgress.setDocument(document);
      documentProgress.setUser(user);
      return documentProgressRepository.save(documentProgress);
    }

    @Override
    public ExamProgress increaseExamProgress(Exam exam, User user) {
        ExamProgress examProgress = new ExamProgress();
        examProgress.setExam(exam);
        examProgress.setUser(user);
        return examProgressRepository.save(examProgress);
    }

    @Override
    @Transactional
    public void decreaseProgress(DecreaseProgressRequest request) {
        if (ProgressType.DOCUMENT.equals(request.getType())) {
            documentProgressRepository.deleteByDocumentIdAndUserId(request.getId(), request.getUserId());
        } else {
            examProgressRepository.deleteByExamIdAndUserId(request.getId(), request.getUserId());
        }
    }

    @Override
    public boolean existsDocumentProgress(String documentId, String userId) {
        return documentProgressRepository.existsByDocumentIdAndUserId(documentId, userId);
    }

    @Override
    public boolean existsExamProgress(String examId, String userId) {
        return examProgressRepository.existsByExamIdAndUserId(examId, userId);
    }

    @Override
    public int getProgressOfCourse(String courseId, String userId) {
        long examCount = examRepository.countByCourseId(courseId);
        long documentCount = documentRepository.countByCourseId(courseId);
        long examProgressCount = examProgressRepository.countByCourseIdAndUserId(courseId, userId);
        long documentProgressCount = documentProgressRepository.countByCourseIdAndUserId(courseId, userId);
        if (examCount + documentCount == 0) {
            return 100;
        }
        return (int) ((examProgressCount + documentProgressCount) / (double) (examCount + documentCount) * 100);
    }

}
