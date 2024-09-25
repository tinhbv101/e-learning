package vn.hcmute.elearning.dtos.chat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.chat.NotifyChatModel;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CountNewMessageResponse extends BaseResponseData {
    private List<NotifyChatModel> notifyChats;
}
