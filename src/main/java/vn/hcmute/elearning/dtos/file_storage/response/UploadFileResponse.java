package vn.hcmute.elearning.dtos.file_storage.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse extends BaseResponseData {
    private String url;
}
