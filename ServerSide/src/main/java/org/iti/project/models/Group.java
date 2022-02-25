package org.iti.project.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Group implements Serializable {
    private int groupId;
    private String groupName;
    private String description;
    private byte[] groupImageBytes;
    private LocalDateTime groupCreationDateTime;
    private List<User> groupMembers;

    public Group(int groupId, String groupName, String description, byte[] groupImageBytes, LocalDateTime groupCreationDateTime, List<User> groupMembers) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupImageBytes = groupImageBytes;
        this.groupCreationDateTime = groupCreationDateTime;
        this.groupMembers = groupMembers;
    }

    public Group(String groupName, String description, byte[] groupImageBytes, int groupId) {
        this.groupName = groupName;
        this.description = description;
        this.groupImageBytes = groupImageBytes;
        this.groupId = groupId;
    }

    public Group(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }

    public Group(String groupName, byte[] groupImageBytes) {
        this.groupName = groupName;
        this.groupImageBytes = groupImageBytes;
    }
    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getGroupImageBytes() {
        return groupImageBytes;
    }

    public void setGroupImageBytes(byte[] groupImageBytes) {
        this.groupImageBytes = groupImageBytes;
    }

    public LocalDateTime getGroupCreationDateTime() {
        return groupCreationDateTime;
    }

    public void setGroupCreationDateTime(LocalDateTime groupCreationDateTime) {
        this.groupCreationDateTime = groupCreationDateTime;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
