package vn.hcmute.elearning.handler.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.chat.request.CreateRoomChatRequest;
import vn.hcmute.elearning.dtos.chat.response.CreateRoomChatResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.entity.chat.ChatRoom;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.model.chat.ChatRoomDto;
import vn.hcmute.elearning.service.chat.ChatRoomService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateRoomChatHandler extends RequestHandler<CreateRoomChatRequest, CreateRoomChatResponse> {
    private final ChatRoomService chatRoomService;
    private final IUserService iUserService;
    private final IUserMapper iUserMapper;

    @Override
    public CreateRoomChatResponse handle(CreateRoomChatRequest request) {
        User userRecipient = iUserService.getUserById(request.getRecipientId());
        if (userRecipient == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        if (request.getRecipientId().equals(request.getUserId())) {
            throw new InternalException(ResponseCode.ROOM_CHAT_CREATE_FAIL);
        }
        String chatId = UUID.randomUUID().toString();
        ChatRoom chatRoomSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(request.getUserId())
                .recipientId(request.getRecipientId())
                .build();
        ChatRoom chatRoomRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(request.getRecipientId())
                .recipientId(request.getUserId())
                .build();
        chatRoomService.save(chatRoomRecipient);
        chatRoomService.save(chatRoomSender);
        return new CreateRoomChatResponse(iUserMapper.toUserDto(userRecipient), new ChatRoomDto(chatRoomSender));
    }
}
