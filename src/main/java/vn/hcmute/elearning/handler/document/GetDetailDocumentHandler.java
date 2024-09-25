package vn.hcmute.elearning.handler.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.document.request.GetDetailDocumentRequest;
import vn.hcmute.elearning.dtos.document.response.GetDetailDocumentResponse;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IDocumentMapper;
import vn.hcmute.elearning.service.interfaces.IDocumentService;

@Component
@RequiredArgsConstructor
public class GetDetailDocumentHandler extends RequestHandler<GetDetailDocumentRequest, GetDetailDocumentResponse> {

    private final IDocumentService documentService;
    private final IDocumentMapper iDocumentMapper;

    @Override
    public GetDetailDocumentResponse handle(GetDetailDocumentRequest request) {
        Document document = documentService.getDocumentById(request.getId());
        if (document == null) {
            throw new InternalException(ResponseCode.DOCUMENT_NOT_FOUND);
        }
        return new GetDetailDocumentResponse(iDocumentMapper.toDocumentDto(document));
    }
}
