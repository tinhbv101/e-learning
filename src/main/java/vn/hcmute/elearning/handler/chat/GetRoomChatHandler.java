package vn.hcmute.elearning.handler.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.chat.request.GetRoomChatRequest;
import vn.hcmute.elearning.dtos.chat.response.GetRoomChatResponse;
import vn.hcmute.elearning.entity.chat.ChatRoom;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.chat.ChatRoomDto;
import vn.hcmute.elearning.service.chat.ChatRoomService;

@Component
@RequiredArgsConstructor
public class GetRoomChatHandler extends RequestHandler<GetRoomChatRequest, GetRoomChatResponse> {
    private final ChatRoomService chatRoomService;

    @Override
    public GetRoomChatResponse handle(GetRoomChatRequest request) {
        ChatRoom chatRoom = chatRoomService.getRoomByUserAndRecipient(request.getUserId(), request.getRecipientId());
        if (chatRoom == null) {
            throw new InternalException(ResponseCode.ROOM_CHAT_NOT_FOUND);
        }
        return new GetRoomChatResponse(new ChatRoomDto(chatRoom));
    }
}
