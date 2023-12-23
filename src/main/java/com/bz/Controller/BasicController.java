package com.bz.Controller;

import com.alibaba.fastjson.JSON;
import com.bz.DTO.UserUpdateDTO;
import com.bz.PoJo.tUser;
import com.bz.Service.UserService;
import com.bz.Vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@Controller
@RequestMapping("/")
public class BasicController {

    @Resource
    private UserService userService;

    @GetMapping("/listPage")//用户列表视图
    public ModelAndView getListPage(@RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("list.html");
        List<tUser> users = userService.listUsers(name);
        modelAndView.addObject("users", users);//"users"对应视图 data: users
        return modelAndView;
    }


    /*控制name的查询条件,URL中携带了name参数，处理POST请求。没有在URL中携带name参数，处理GET请求。
    注意视图中axios请求改method        http://localhost:3306?name=xxx     */
    @GetMapping("/listUsers")//查询用户列表
    @ResponseBody
    public Result<Object> listUsers(@RequestParam(value = "name", required = false) String name) {
        List<tUser> users = userService.listUsers(name);
        return Result.success(users);
    }


    @GetMapping("/toAddUserPage")//添加用户视图
    public ModelAndView toAddUserPage() {
        return new ModelAndView("add.html");
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Result<Object> addUser(@RequestBody tUser user) {
        userService.addUser(user);
        return Result.success("添加新用户成功");
    }

    @GetMapping("/toEditUserPage")
    public ModelAndView toEditUserPage(@RequestParam("id") Integer userId) {
        tUser user = userService.getUserById(userId);
        ModelAndView modelAndView = new ModelAndView("edit.html");
        System.out.println(user);
        return modelAndView;
    }

    @GetMapping("/getUserById")//回显数据
    @ResponseBody
    public Result<Object> getUserById(@RequestParam("id") Integer userId) {
        tUser user = userService.getUserById(userId);
        return Result.success(user);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public Result<Object> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        tUser user = userUpdateDTO.toTUser();
        userService.updateUser(user);
        return Result.success("用户信息更新成功");
    }


    @DeleteMapping("/deleteUsers")
    @ResponseBody //不要漏了返回json数据
    public Result<Object> deleteUsers(@RequestParam("ids") String ids) {
        String[] idList = ids.split(",");
        userService.deleteUsers(idList);

        return Result.success("删除用户成功");
    }

}
