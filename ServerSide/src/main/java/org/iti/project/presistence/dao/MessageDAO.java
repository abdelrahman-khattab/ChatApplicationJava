package org.iti.project.presistence.dao;

import org.iti.project.models.SingleMessage;
import org.iti.project.presistence.entities.Message;

import java.util.List;

public interface MessageDAO extends DAO{
    void insertMessage(Message message);
    Message selectMessage(Message message);

    List<SingleMessage> restoreSingleMessages();

}
