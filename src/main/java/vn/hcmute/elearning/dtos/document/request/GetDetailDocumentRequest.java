package vn.hcmute.elearning.dtos.document.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetDetailDocumentRequest extends BaseRequestData {
    private String id;
}
