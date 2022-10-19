package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.service.GroupService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public Response addGroup(GroupDetails groupDetails) {
        Response response = new Response();
        try {
            GroupDetails group = groupDao.getByGroupName(groupDetails.getName());
            if (group == null) {
                groupDao.save(groupDetails);
                return new Response("200", "Group Added SuccessFully", new ArrayList<>());
            } else {
                response.setMessage("Failed");
                response.setStatus("500");
                return new Response("500", "Group Not Added SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response updateGroup(Integer groupId, GroupDetails groupDetails) {
        Response response = new Response();
        try {
            Optional<GroupDetails> group = groupDao.findById(groupId);
            if (group.isPresent()) {
                group.get().setName(groupDetails.getName());
                groupDao.save(group.get());
                return new Response("200", "Group Updated SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Group Not Updated", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response findAllGroups() {
        Response response = new Response();
        try {
            List<GroupDetails> group = groupDao.findAll();
            //response.setResultObj(group);
            return new Response("200", "Data Fetched SuccessFully", group);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findGroupById(Integer groupId) {
        Response response = new Response();
        try {
            Optional<GroupDetails> group = groupDao.findById(groupId);
            if (group.isPresent()) {
                //response.setResultObj(group.get());
                new Response("200", "Data Fetched SuccessFully", group.get());
            } else {
                new Response("500", "No Data Found", group);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response deleteGroup(Integer groupId) {
        Response response = new Response();
        try {
            Optional<GroupDetails> group = groupDao.findById(groupId);
            if (group.isPresent()) {
                groupDao.delete(group.get());
                response.setMessage("success");
                response.setStatus("200");
                return new Response("200", "Group Deleted SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Group Not Deleted SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }
}
