package com.liuk.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuk.blog.constant.WebConst;
import com.liuk.blog.dao.LogVoMapper;
import com.liuk.blog.model.vo.LogVo;
import com.liuk.blog.model.vo.LogVoExample;
import com.liuk.blog.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Liuk On 2018/05/26.
 */
@Service
public class LogServiceImpl implements ILogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private LogVoMapper logDao;
    @Override
    public void insertLog(LogVo logVo) {

    }

    @Override
    public void insertLog(String action, String data, String ip, Integer authorId) {

    }

    @Override
    public List<LogVo> getLogs(int page, int limit) {
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        LogVoExample example = new LogVoExample();
        example.setOrderByClause("id desc");
        PageHelper.startPage((page - 1) * limit, limit);
        List<LogVo> logPage = logDao.selectByExample(example);
        LOGGER.debug("Exit getLogs method");
        return logPage;
    }
}
