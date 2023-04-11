package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.service.GroupService;
import com.studentdemo.studentdemo.util.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupTest {


    @Autowired
    GroupDao groupDao;

    @Autowired
    GroupService groupService;


    @Test
    @Order(1)
    public void addGroupApiTest() {
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(3);
        groupDetails.setName("UI");
        Response actualResponse = groupService.addGroup(groupDetails);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(2)
    public void updateGroupAPITest() {
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(3);
        groupDetails.setName("ui");
        Response actualResponse = groupService.updateGroup(groupDetails.getGroupId(), groupDetails);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(3)
    public void findGroupByIdAPITest() {
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(3);
        Response response = groupService.findGroupById(groupDetails.getGroupId());
        assertNotEquals(null, response);
    }


    @Test
    @Order(4)
    public void findAllGroupsAPITest() {
        Response actualResponse = groupService.findAllGroups();
        List<GroupDetails> expectedResponse = new ArrayList<>();
        assertNotEquals(expectedResponse, actualResponse);
    }

    @Test
    @Order(5)
    public void deleteGroupAPITest() {
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(4);
        Response response = groupService.deleteGroup(groupDetails.getGroupId());
        assertNotEquals(null, response);
    }
}
