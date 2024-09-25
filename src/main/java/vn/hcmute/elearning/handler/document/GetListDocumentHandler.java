package vn.hcmute.elearning.handler.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.document.request.GetLIstDocumentRequest;
import vn.hcmute.elearning.dtos.document.response.GetListDocumentResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IDocumentMapper;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IDocumentService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetListDocumentHandler extends RequestHandler<GetLIstDocumentRequest, GetListDocumentResponse> {
    private final IDocumentService iDocumentService;
    private final IDocumentMapper iDocumentMapper;
    private final ICourseService iCourseService;

    @Override
    public GetListDocumentResponse handle(GetLIstDocumentRequest request) {
        Course course = iCourseService.getByTeacherOrUser(request.getCourseId(), request.getUserId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_TEACHER_OR_USER_REGISTER);
        }
        List<Document> documents = iDocumentService.getByLessonId(request.getLessonId());
        return new GetListDocumentResponse(iDocumentMapper.toListDocumentDto(documents));
    }
}
