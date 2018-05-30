package com.liuk.blog.service.impl;

import com.liuk.blog.dao.UserVoMapper;
import com.liuk.blog.exception.TipException;
import com.liuk.blog.model.vo.UserVo;
import com.liuk.blog.model.vo.UserVoExample;
import com.liuk.blog.service.IUserService;
import com.liuk.blog.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Liuk On 2018/05/20.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserVoMapper userDao;

    @Override
    public Integer insertUser(UserVo userVo) {
        return null;
    }

    @Override
    public UserVo queryUserById(Integer uid) {
        return null;
    }

    @Override
    public UserVo login(String username, String password) {
        UserVoExample example = new UserVoExample();
        UserVoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        int count = userDao.countByExample(example);
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        String pwd = TaleUtils.MD5encode(username + password);
        criteria.andPasswordEqualTo(pwd);
        List<UserVo> userVoList = userDao.selectByExample(example);
        if (userVoList.size() != 1) {
            throw new TipException("密码错误");
        }
        return userVoList.get(0);
    }

    @Override
    public void updateByUid(UserVo userVo) {

    }

    @Override
    public UserVo queryUserByName(String username) {
        UserVoExample example = new UserVoExample();
        UserVoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UserVo> userVoList = userDao.selectByExample(example);
        if(userVoList.size() > 0) {
            return userVoList.get(0);
        } else {
            return null;
        }
    }

}
