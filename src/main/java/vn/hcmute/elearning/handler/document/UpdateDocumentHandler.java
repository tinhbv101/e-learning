package vn.hcmute.elearning.handler.document;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.document.request.UpdateDocumentRequest;
import vn.hcmute.elearning.dtos.document.response.UpdateDocumentResponse;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DocumentType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IDocumentMapper;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.service.interfaces.IDocumentService;

@Component
@RequiredArgsConstructor
public class UpdateDocumentHandler extends RequestHandler<UpdateDocumentRequest, UpdateDocumentResponse> {
    private final IDocumentService documentService;
    private final IDocumentMapper iDocumentMapper;
    private final MinIOService minIOService;

    @SneakyThrows
    @Override
    public UpdateDocumentResponse handle(UpdateDocumentRequest request) {
        Document document = documentService.getDocumentByIdAndCreateBy(request.getId(), request.getUserId());
        if (document == null) {
            throw new InternalException(ResponseCode.DOCUMENT_NOT_FOUND);
        }
        Lesson lesson = document.getLesson();
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
        if (request.getDocumentType().equals(DocumentType.VIDEO)) {
            document.setDocumentUrl(request.getVideoUrl());
        }
        document = documentService.createDocument(document);
        return new UpdateDocumentResponse(iDocumentMapper.toDocumentDto(document));
    }
}
