package org.example.backend.service.serviceImpl.user;

import java.util.Collections;
import java.util.List;
import org.example.backend.entity.user.Consultation;
import org.example.backend.entity.user.Message;
import org.example.backend.mapper.user.MessageMapper;
import org.example.backend.service.user.ConsultationService;
import org.example.backend.service.user.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

  @Autowired
  private MessageMapper messageMapper;

  @Autowired
  private ConsultationService consultationService;

  @Override
  public List<Message> selectMessagesById(String doctorId, String userId) {
    try {
      Consultation consultation = consultationService.selectConsultationByDoctorIdAndUserId(doctorId, userId);
      Integer consultationId = consultation.getConsultationId();
      return messageMapper.selectById(consultationId);
    } catch (Exception e) {
      // 记录异常日志
      logger.error("获取咨询信息失败, doctorId: {}, userId: {}", doctorId, userId, e);
      return Collections.emptyList();
    }
  }
}
