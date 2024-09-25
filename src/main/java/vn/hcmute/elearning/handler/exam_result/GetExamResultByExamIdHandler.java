package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam_result.request.GetExamResultByExamIdRequest;
import vn.hcmute.elearning.dtos.exam_result.response.GetDetailExamResultResponse;
import vn.hcmute.elearning.dtos.exam_result.response.GetExamResultByExamIdResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.specifications.ExamResultSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetExamResultByExamIdHandler extends RequestHandler<GetExamResultByExamIdRequest, GetExamResultByExamIdResponse> {
    private final IExamResultService examResultService;
    private final IExamService examService;
    private final ExamResultSpecification examResultSpecification;
    @Override
    public GetExamResultByExamIdResponse handle(GetExamResultByExamIdRequest request) {
        Exam exam = examService.getExamById(request.getExamId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        Specification<ExamResult> specification = Specification
            .where(examResultSpecification.equalExamId(exam.getId()));
        Page<ExamResult> page = examResultService.getAll(specification, request.getPageable());
        List<GetDetailExamResultResponse> listResponse = page.getContent().parallelStream().map(GetDetailExamResultResponse::new).collect(Collectors.toList());
        return new GetExamResultByExamIdResponse(listResponse, new Paginate(page));
    }
}
