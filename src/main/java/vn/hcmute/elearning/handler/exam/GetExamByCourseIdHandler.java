package vn.hcmute.elearning.handler.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam.request.GetExamByLessonRequest;
import vn.hcmute.elearning.dtos.exam.response.ExamResponse;
import vn.hcmute.elearning.dtos.exam.response.GetExamByLessonResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetExamByCourseIdHandler extends RequestHandler<GetExamByLessonRequest, GetExamByLessonResponse> {
    private final IExamService examService;
    @Override
    public GetExamByLessonResponse handle(GetExamByLessonRequest request) {
        String lessonId = request.getLessonId();
        Page<Exam> page = examService.getExamByLessonId(lessonId, request.getPageable());
        List<ExamResponse> list = page.getContent().parallelStream().map(ExamResponse::new).collect(Collectors.toList());
        return new GetExamByLessonResponse(list, new Paginate(page));
    }
}
