package vn.hcmute.elearning.handler.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.chat.request.CountNewMessageRequest;
import vn.hcmute.elearning.dtos.chat.response.CountNewMessageResponse;
import vn.hcmute.elearning.entity.chat.ChatRoom;
import vn.hcmute.elearning.model.chat.NotifyChatModel;
import vn.hcmute.elearning.service.chat.ChatMessageService;
import vn.hcmute.elearning.service.chat.ChatRoomService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CountNewMessageHandler extends RequestHandler<CountNewMessageRequest, CountNewMessageResponse> {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @Override
    public CountNewMessageResponse handle(CountNewMessageRequest request) {
        List<NotifyChatModel> notifyChats = new ArrayList<>();
        request.getSenderIds().forEach(senderId -> {
            ChatRoom chatRoom = chatRoomService.getRoomByUserAndRecipient(request.getUserId(), senderId);
            notifyChats.add(new NotifyChatModel(chatRoom.getRecipientId(), chatMessageService.countMessage(chatRoom.getRecipientId(), chatRoom.getChatId(), false)));
        });

        return new CountNewMessageResponse(notifyChats);
    }
}
