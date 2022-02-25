package org.iti.project.presistence.dao;
import org.iti.project.models.GroupMessage;
import org.iti.project.models.SingleMessage;

import java.util.List;

public interface MessageDAO extends DAO{
    void storeSingleMessage(SingleMessage message);
    void storeGroupMessage(GroupMessage message);
     List<SingleMessage> restoreSingleMessages(String senderPhone , String receiverPhone);
     List<GroupMessage> restoreGroupMessages(int groupReceipentId);

}
