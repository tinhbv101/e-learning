package vn.hcmute.elearning.handler.ekyc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.ekyc.request.DetectIdCardRequest;
import vn.hcmute.elearning.dtos.ekyc.response.DetectIdCardResponse;
import vn.hcmute.elearning.entity.Ekyc;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IEkycMapper;
import vn.hcmute.elearning.model.IdCardModel;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.service.interfaces.IEkycService;

import javax.transaction.Transactional;

import static vn.hcmute.elearning.common.Constant.getOCRObjectName;

@Component
@RequiredArgsConstructor
@Slf4j
public class DetectIdCardHandler extends RequestHandler<DetectIdCardRequest, DetectIdCardResponse> {
    private final IEkycService ekycService;
    private final IEkycMapper ekycMapper;
    private final MinIOService minIOService;

    @Override
    @Transactional
    public DetectIdCardResponse handle(DetectIdCardRequest request) {
        MultipartFile imageFront = request.getImageFront();
        MultipartFile imageBack = request.getImageBack();

        IdCardModel idCardModel = ekycService.detectIdCard(imageFront, imageBack);
        Ekyc ekyc = ekycMapper.idCardModelToEkyc(idCardModel);
        log.debug("data detect ekyc: {}", ekyc);

        // upload image to minio
        long timestamp = System.currentTimeMillis();
        String extensionFront = FilenameUtils.getExtension(imageFront.getOriginalFilename());
        String extensionBack = FilenameUtils.getExtension(imageBack.getOriginalFilename());
        String imageFrontUrl = String.format("%s_%s.%s", imageFront.getResource().getFilename(), timestamp, extensionFront);
        String imageBackUrl = String.format("%s_%s.%s", imageBack.getResource().getFilename(), timestamp, extensionBack);

        String objectFront = getOCRObjectName(imageFrontUrl);
        String objectBack = getOCRObjectName(imageBackUrl);
        try {
            objectFront = minIOService.uploadFile(objectFront, imageFront.getBytes(), extensionFront, false);
            objectBack = minIOService.uploadFile(objectBack, imageBack.getBytes(), extensionBack, false);
        } catch (Exception ex) {
            log.error("Upload file to minio service error: {}", ex.getMessage());
            throw new InternalException(ResponseCode.UPLOAD_FILE_FAILED);
        }

        Ekyc ekycOld = ekycService.getByUserId(request.getUserId());
        if (ekycOld != null) {
            ekyc.setId(ekycOld.getId());
        }
        ekyc.setFrontUrl(objectFront);
        ekyc.setBackUrl(objectBack);
        Ekyc ekycSave = ekycService.save(ekyc);
        return new DetectIdCardResponse(ekycMapper.toEkycDTO(ekycSave));
    }
}
