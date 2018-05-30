package com.liuk.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuk.blog.dao.AttachVoMapper;
import com.liuk.blog.dao.CommentVoMapper;
import com.liuk.blog.dao.ContentVoMapper;
import com.liuk.blog.dao.MetaVoMapper;
import com.liuk.blog.dto.MetaDto;
import com.liuk.blog.dto.Types;
import com.liuk.blog.model.bo.ArchiveBo;
import com.liuk.blog.model.bo.BackResponseBo;
import com.liuk.blog.model.bo.StatisticsBo;
import com.liuk.blog.model.vo.*;
import com.liuk.blog.service.ISiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Liuk On 2018/05/26.
 */
@Service
public class SiteServiceImpl implements ISiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private CommentVoMapper commentDao;

    @Autowired
    private ContentVoMapper contentDao;

    @Autowired
    private AttachVoMapper attachDao;

    @Autowired
    private MetaVoMapper metaDao;

    /**
     * 最新收到的评论
     * @param limit
     * @return
     */
    @Override
    public List<CommentVo> recentComments(int limit) {
        LOGGER.debug("Enter recentComments method:limit={}", limit);
        if(limit<0 || limit>10) {
            limit = 10;
        }
        CommentVoExample example = new CommentVoExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(1,limit);
        List<CommentVo> listPage = commentDao.selectByExampleWithBLOBs(example);
        LOGGER.debug("Exit recentComments method");
        return listPage;
    }

    /**
     * 最新发表的文章
     * @param limit
     * @return
     */
    @Override
    public List<ContentVo> recentContents(int limit) {
        LOGGER.debug("Enter recentContents method:limit={}", limit);
        if(limit<0 || limit>10) {
            limit = 10;
        }
        ContentVoExample example = new ContentVoExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(1,limit);
        List<ContentVo> listPage = contentDao.selectByExampleWithBLOBs(example);
        LOGGER.debug("Exit recentContents method");
        return listPage;
    }

    /**
     * 查询一条评论
     * @param coid
     * @return
     */
    @Override
    public CommentVo getComment(Integer coid) {
        return null;
    }

    /**
     * 系统备份
     * @param bk_type
     * @param bk_path
     * @param fmt
     * @return
     * @throws Exception
     */
    @Override
    public BackResponseBo backup(String bk_type, String bk_path, String fmt) throws Exception {
        return null;
    }

    /**
     * 获取后台统计数据
     * @return
     */
    @Override
    public StatisticsBo getStatistics() {
        LOGGER.debug("Enter getStatistics method");
        StatisticsBo statistics = new StatisticsBo();
        ContentVoExample contentVoExample = new ContentVoExample();

        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        Long articles = contentDao.countByExample(contentVoExample);
        Long comments = commentDao.countByExample(new CommentVoExample());
        Long attachs = attachDao.countByExample(new AttachVoExample());

        MetaVoExample metaVoExample = new MetaVoExample();
        metaVoExample.createCriteria().andTypeEqualTo(Types.LINK.getType());
        Long links = metaDao.countByExample(metaVoExample);
        statistics.setArticles(articles);
        statistics.setComments(comments);
        statistics.setAttachs(attachs);
        statistics.setLinks(links);

        LOGGER.debug("Exit getStatistics method");
        return statistics;
    }

    /**
     * 查询文章归档
     * @return
     */
    @Override
    public List<ArchiveBo> getArchives() {
        return null;
    }

    /**
     * 获取分类/标签列表
     * @param type
     * @param orderBy
     * @param limit
     * @return
     */
    @Override
    public List<MetaDto> metas(String type, String orderBy, int limit) {
        return null;
    }
}
