package com.bz.Service;

import com.bz.PoJo.tUser;

import java.util.List;

public interface UserService {

    List<tUser> listUsers(String name);

    void addUser(tUser user);

    tUser getUserById(Integer userId);

    void updateUser(tUser user);

    void deleteUsers(String[] idList);

}
