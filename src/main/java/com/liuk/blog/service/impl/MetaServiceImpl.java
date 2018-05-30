package com.liuk.blog.service.impl;

import com.liuk.blog.constant.WebConst;
import com.liuk.blog.dao.MetaVoMapper;
import com.liuk.blog.dto.MetaDto;
import com.liuk.blog.model.vo.MetaVo;
import com.liuk.blog.service.IMetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Liuk On 2018/05/30.
 */
@Service
public class MetaServiceImpl implements IMetaService {
    @Autowired
    private MetaVoMapper metaDao;
    @Override
    public MetaDto getMeta(String type, String name) {
        return null;
    }

    @Override
    public Integer countMeta(Integer mid) {
        return null;
    }

    @Override
    public List<MetaVo> getMetas(String types) {
        return null;
    }

    @Override
    public void saveMetas(Integer cid, String names, String type) {

    }

    @Override
    public void saveMeta(String type, String name, Integer mid) {

    }

    @Override
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        if(StringUtils.isNotBlank(type)) {
            if(StringUtils.isNotBlank(orderby)) {
                orderby = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderby);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    @Override
    public void delete(int mid) {

    }

    @Override
    public void saveMeta(MetaVo metas) {

    }

    @Override
    public void update(MetaVo metas) {

    }
}
