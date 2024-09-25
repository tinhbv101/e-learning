package vn.hcmute.elearning.dtos.chat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.chat.ChatRoomDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetRoomChatResponse extends BaseResponseData {
    private ChatRoomDto chatRoom;
}
