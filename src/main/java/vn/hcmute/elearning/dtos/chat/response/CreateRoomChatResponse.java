package vn.hcmute.elearning.dtos.chat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.chat.ChatRoomDto;
import vn.hcmute.elearning.model.user.UserDto;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomChatResponse extends BaseResponseData {
    private UserDto user;
    private ChatRoomDto chatRoom;
}
