package gov.iti.jets.project.presistence.dao;

import gov.iti.jets.project.presistence.entities.Message;

public interface MessageDAO extends DAO{
    void insertMessage(Message message);
    Message selectMessage(Message message);

}
