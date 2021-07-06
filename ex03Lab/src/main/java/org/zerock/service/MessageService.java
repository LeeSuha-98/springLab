package org.zerock.service;

import org.zerock.domain.MessageVO;

public interface MessageService {

  public void addMessage(MessageVO vo) throws Exception; //인터페이스는 addmessage
  

  public MessageVO readMessage(String uid, Integer mno) throws Exception;
}
