package com.bz.Service.Impl;

import com.bz.PoJo.tUser;
import com.bz.Service.UserService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    SQLManager sqlManager;


    @Override
    public List<tUser> listUsers(String name) {
        LambdaQuery<tUser> query = sqlManager.lambdaQuery(tUser.class);
        /*if (!ObjectUtils.isEmpty(name)) {//name不为空或不为空串,搜索传入的name
            query.andLike(tUser::getName, "%" + name + "%");
        }*/
        //对应视图都走 搜索按钮提交事件
        query.andLike(tUser::getName, Query.filterEmpty("%" + name + "%"));//name空不执行查询
        List<tUser> userList = query.select();
        return userList;
    }

    @Override
    public void addUser(tUser user) {
        sqlManager.insert(user);
    }


    /*
     * 根据id查询用户,编辑用户回显
     */
    @Override
    public tUser getUserById(Integer userId) {
        Query<tUser> query = sqlManager.query(tUser.class);
        tUser user = query.andEq("id", userId).single();
        return user;

    }

    @Override
    public void updateUser(tUser user) {
        Query<tUser> query = sqlManager.query(tUser.class);
        query.andEq("id", user.getId()).update(user);

    }


    @Override
    public void deleteUsers(String[] idList) {
        Query<tUser> query = sqlManager.query(tUser.class);
        for (String id : idList) {
            query.orEq("id", Integer.parseInt(id));  //转换id整数类型
        }
        query.delete();

    }



}
