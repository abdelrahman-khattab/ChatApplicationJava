package org.iti.project.presistence.dao;

import org.iti.project.presistence.entities.Message;

public interface MessageDAO extends DAO{
    void insertMessage(Message message);
    Message selectMessage(Message message);

}
