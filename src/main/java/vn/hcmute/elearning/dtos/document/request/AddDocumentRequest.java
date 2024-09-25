package vn.hcmute.elearning.dtos.document.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.DocumentType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddDocumentRequest extends BaseRequestData {
    @NotBlank
    private String lessonId;
    @NotBlank
    private String documentName;
    @NotNull
    private DocumentType documentType;
    private String content;
    private MultipartFile file;
    private String videoUrl;
    private String description;
}
