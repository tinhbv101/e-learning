package vn.hcmute.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IDocumentController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.document.request.*;
import vn.hcmute.elearning.dtos.document.response.AddDocumentResponse;
import vn.hcmute.elearning.dtos.document.response.GetDetailDocumentResponse;
import vn.hcmute.elearning.dtos.document.response.GetListDocumentResponse;
import vn.hcmute.elearning.dtos.document.response.UpdateDocumentResponse;

@RestController
public class DocumentController extends BaseController implements IDocumentController {
    @Override
    public ResponseEntity<ResponseBase<GetDetailDocumentResponse>> getDetailDocument(String id) {
        GetDetailDocumentRequest request = new GetDetailDocumentRequest();
        request.setId(id);
        return this.execute(request, GetDetailDocumentResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AddDocumentResponse>> createDocument(AddDocumentRequest request) {
        return this.execute(request, AddDocumentResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateDocumentResponse>> updateDocument(UpdateDocumentRequest request) {
        return this.execute(request, UpdateDocumentResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteDocument(String id) {
        DeleteDocumentRequest request = new DeleteDocumentRequest();
        request.setId(id);
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetListDocumentResponse>> getDocuments(GetLIstDocumentRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> displayDocument(DisplayDocumentRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetListDocumentResponse>> getDocumentForStudent(String lessonId) {
        return this.execute(new GetDocumentForStudentRequest(lessonId));
    }
}
