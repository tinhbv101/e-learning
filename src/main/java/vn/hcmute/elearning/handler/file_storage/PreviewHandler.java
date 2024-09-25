package vn.hcmute.elearning.handler.file_storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.file_storage.request.PreviewRequest;
import vn.hcmute.elearning.dtos.file_storage.response.PreviewResponse;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.utils.CommonUtils;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class PreviewHandler extends RequestHandler<PreviewRequest, PreviewResponse> {
    private final MinIOService minIOService;

    @Override
    public PreviewResponse handle(PreviewRequest request) {
        try {
            InputStream fileRes = minIOService.getFile(request.getObjectName());
            return new PreviewResponse()
                    .setBytes(fileRes.readAllBytes())
                    .setFilename(request.getObjectName())
                    .setContentType(CommonUtils.getTypeImage(request.getObjectName()));
        } catch (Exception ex) {
            log.error("get file to service minio error: {}", ex.getMessage());
            return null;
        }
    }
}
