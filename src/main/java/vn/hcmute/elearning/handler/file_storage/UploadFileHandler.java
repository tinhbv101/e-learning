package vn.hcmute.elearning.handler.file_storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.file_storage.request.UploadFileRequest;
import vn.hcmute.elearning.dtos.file_storage.response.UploadFileResponse;
import vn.hcmute.elearning.service.interfaces.IFileStorageService;

@Component
@RequiredArgsConstructor
public class UploadFileHandler extends RequestHandler<UploadFileRequest, UploadFileResponse> {
    private final IFileStorageService fileStorageService;

    @Override
    public UploadFileResponse handle(UploadFileRequest request) {
        String urlResponse =  fileStorageService.uploadFile(request.getFile());
        return new UploadFileResponse(urlResponse);
    }
}
