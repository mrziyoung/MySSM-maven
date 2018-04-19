package com.young.ssm.service.impl;

import com.young.ssm.common.Constants;
import com.young.ssm.dao.UserDao;
import com.young.ssm.entity.User;
import com.young.ssm.redis.RedisUtil;
import com.young.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private RedisUtil redisUtil;


    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> findUser(Map<String, Object> map) {
        return userDao.findUsers(map);
    }

    @Override
    public int updateUser(User user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if ("admin".equals(user.getUserName())) {
            return 0;
        }
        if (user.getUserName() == null || user.getPassword() == null ) {
            return 0;
        }
        //删除缓存数据后再
        if (userDao.updateUser(user) > 0) {
            redisUtil.del(Constants.USER_CACHE_KEY + user.getId());
            redisUtil.put(Constants.USER_CACHE_KEY +  user.getId(), user);
            return 1;
        }
        return 0;

    }

    @Override
    public Long getTotalUser(Map<String, Object> map) {
        return userDao.getTotalUser(map);
    }

    @Override
    public int addUser(User user) {
        if (user.getUserName() == null || user.getPassword() == null || getTotalUser(null) > 90) {
            return 0;
        }
        if(userDao.addUser(user)>0){
            redisUtil.put(Constants.USER_CACHE_KEY + user.getId(),user);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteUser(Integer id) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if (2 == id) {
            return 0;
        }
        return userDao.deleteUser(id);
    }

}
