package org.iti.project.presistence.dao;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;
public interface MessageDAO extends DAO{
    void storeSingleMessage(SingleMessage message);
    void storeGroupMessage(GroupMessage message);
}
