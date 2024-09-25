package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.course.request.IncreaseProgressRequest;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ProgressType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IDocumentService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IProgressService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class IncreaseProgressHandler extends RequestHandler<IncreaseProgressRequest, StatusResponse> {

    private final IProgressService progressService;
    private final IDocumentService documentService;
    private final IExamService examService;
    private final IUserService userService;

    @Override
    public StatusResponse handle(IncreaseProgressRequest request) {
        User user = userService.getUserByIdNotNull(request.getUserId());
        if (ProgressType.DOCUMENT.equals(request.getType())) {
            Document document = documentService.getDocumentById(request.getId());
            if (document == null) {
                throw new InternalException(ResponseCode.DOCUMENT_NOT_FOUND);
            }
            progressService.increaseDocumentProgress(document, user);
            return new StatusResponse(true);
        }
        Exam exam = examService.getExamById(request.getId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        progressService.increaseExamProgress(exam, user);
        return new StatusResponse(true);
    }
}
