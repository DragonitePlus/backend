package org.example.backend.service.others;

import java.util.List;
import org.example.backend.entity.doctor.DoctorUserRelation;
import org.example.backend.entity.others.Message;

public interface MessageService {

  int insertMessage(Message message);

  Message getLastMessage(Integer relationId);

  List<Message> getLast30Messages(Integer relationId);

  List<Message> getNew30Messages(Integer relationId);

  List<Message> getMessagesAfterSeq(Integer relationId, Integer messageSeq);

  List<Message> getMessagesBeforeSeq(Integer relationId, Integer messageSeq);

  Message sendMessage(
      Integer relationId, String senderType, String messageText, String messageType, String url);

  int countUnreadMessages(int relationId, int readSeg, String senderType);

  int todayCousultationUserCount(String doctorId);

  public String getReadInfoSeq(int relationId);

  public boolean updateReadInfoSeq(String userId,int relationId,int ReadSeq);
}
