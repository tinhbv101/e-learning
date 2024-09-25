package vn.hcmute.elearning.dtos.document.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.DocumentDto;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AddDocumentResponse extends BaseResponseData {
    private DocumentDto document;

}
