package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.util.Response;

public interface GroupService {
    Response addGroup(GroupDetails groupDetails);

    Response updateGroup(Integer groupId, GroupDetails groupDetails);

    Response findAllGroups();

    Response findGroupById(Integer groupId);

    Response deleteGroup(Integer groupId);
}
