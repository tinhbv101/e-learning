package vn.hcmute.elearning.handler.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.document.request.DeleteDocumentRequest;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IDocumentService;

@Component
@RequiredArgsConstructor
public class DeleteDocumentHandler extends RequestHandler<DeleteDocumentRequest, StatusResponse> {

    private final IDocumentService documentService;

    @Override
    public StatusResponse handle(DeleteDocumentRequest request) {
        Document document = documentService.getDocumentById(request.getId());
        if (document == null) {
            throw new InternalException(ResponseCode.DOCUMENT_NOT_FOUND);
        }
        documentService.deleteDocumentById(request.getId());
        return new StatusResponse(true);
    }
}
