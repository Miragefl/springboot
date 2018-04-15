package com.viscum.chapter02.controller;

import com.viscum.chapter02.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * RESTful API具体设计如下
 * <p>
 * GET: query userList
 * POST:create User
 * GET: query user by id
 * PUT: update user by id
 * DELETE: delete user by id
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // 创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> list = new ArrayList<User>(users.values());
        return list;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) {
        /**
         *  除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
         */
        System.out.println(user.getId());
        users.put(user.getId(), user);
        return "SUCCESS";
    }

    @RequestMapping(value = "/qryUserById/{id}", method = RequestMethod.GET)
    public User qryUserById(@PathVariable Long id) {
        System.out.println(id);
        User user = users.get(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/updateUserById/{id}", method = RequestMethod.PUT)
    public User updateUserById(@PathVariable long id, @ModelAttribute User user) {
        User _user = users.get(id);
        _user.setAge(user.getAge());
        _user.setName(user.getName());
        users.put(id, _user);
        return _user;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable long id) {
        users.remove(id);
        System.out.println(users.size());
        return "SUCCESS";
    }

}
