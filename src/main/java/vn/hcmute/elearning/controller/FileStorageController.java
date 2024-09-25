package vn.hcmute.elearning.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IFileStorageController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.file_storage.request.MultiUploadRequest;
import vn.hcmute.elearning.dtos.file_storage.request.PreviewRequest;
import vn.hcmute.elearning.dtos.file_storage.request.UploadFileRequest;
import vn.hcmute.elearning.dtos.file_storage.response.MultiUploadResponse;
import vn.hcmute.elearning.dtos.file_storage.response.PreviewResponse;
import vn.hcmute.elearning.dtos.file_storage.response.UploadFileResponse;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;

@RestController
public class FileStorageController extends BaseController implements IFileStorageController {
    @Override
    public ResponseEntity<ResponseBase<UploadFileResponse>> upload(UploadFileRequest request) {
        return this.execute(request, UploadFileResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<MultiUploadResponse>> multiUpload(MultiUploadRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ByteArrayResource> preview(PreviewRequest request) {
        ResponseBase<PreviewResponse> responseBase = this.execute(request, PreviewResponse.class).getBody();

        if (responseBase == null) {
            throw new InternalException(ResponseCode.FAILED);
        }
        PreviewResponse response = responseBase.getData();
        ByteArrayResource resource = new ByteArrayResource(response.getBytes());
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(response.getContentType())
                .body(resource);
    }
}
