package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam_result.request.GetUngradedExamsRequest;
import vn.hcmute.elearning.dtos.exam_result.response.GetUngradedExamsResponse;
import vn.hcmute.elearning.dtos.exam_result.response.UngradedExamResponse;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.repository.ExamResultRepository;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.utils.Paginate;

@Component
@RequiredArgsConstructor
public class GetUngradedExamsHandler extends RequestHandler<GetUngradedExamsRequest, GetUngradedExamsResponse> {

    private final ExamResultRepository examResultRepository;
    private final ITeacherService teacherService;
    @Override
    public GetUngradedExamsResponse handle(GetUngradedExamsRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        Page<UngradedExamResponse> examResponses = examResultRepository.findUngradedExams(request.getExamId(), teacher.getId(), request.getStatus(), request.getPageable());
        return new GetUngradedExamsResponse(examResponses.getContent(), new Paginate(examResponses));
    }
}
