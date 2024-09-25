package vn.hcmute.elearning.handler.document;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.document.request.AddDocumentRequest;
import vn.hcmute.elearning.dtos.document.response.AddDocumentResponse;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DisplayStatus;
import vn.hcmute.elearning.enums.DocumentType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IDocumentMapper;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.service.interfaces.IDocumentService;
import vn.hcmute.elearning.service.interfaces.ILessonService;

@Component
@RequiredArgsConstructor
public class AddDocumentHandler extends RequestHandler<AddDocumentRequest, AddDocumentResponse> {
    private final IDocumentService documentService;
    private final MinIOService minIOService;
    private final ILessonService iLessonService;
    private final IDocumentMapper iDocumentMapper;


    @SneakyThrows
    @Override
    public AddDocumentResponse handle(AddDocumentRequest request) {
        Lesson lesson = iLessonService.getByIdAndCreateBy(request.getLessonId(), request.getUserId());
        if (lesson == null) {
            throw new InternalException(ResponseCode.LESSON_NOT_FOUND);
        }
        Document document = new Document();
        MultipartFile file = request.getFile();
        if (file != null) {
            String objectName = String.format(
                    "%s_%s_%s_%s.%s", lesson.getCourse().getCourseName(), lesson.getName(),
                    file.getResource().getFilename(), System.currentTimeMillis(), FilenameUtils.getExtension(file.getOriginalFilename())
            );
            objectName = minIOService.uploadFile(objectName, file.getBytes(), file.getContentType(), true);
            document.setDocumentUrl(objectName);
        }
        document.setLesson(lesson);
        document.setDocumentName(request.getDocumentName());
        document.setContent(request.getContent());
        document.setDescription(request.getDescription());
        document.setDocumentType(request.getDocumentType());
        document.setDisplayStatus(DisplayStatus.HIDE);
        if (request.getDocumentType().equals(DocumentType.VIDEO)) {
            document.setDocumentUrl(request.getVideoUrl());
        }
        document = documentService.createDocument(document);
        return new AddDocumentResponse(iDocumentMapper.toDocumentDto(document));
    }
}
