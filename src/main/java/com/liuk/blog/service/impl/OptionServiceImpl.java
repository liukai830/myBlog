package com.liuk.blog.service.impl;

import com.liuk.blog.model.vo.OptionVo;
import com.liuk.blog.service.IOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created By Liuk On 2018/05/30.
 */
@Service
public class OptionServiceImpl implements IOptionService {
    @Override
    public void insertOption(OptionVo optionVo) {

    }

    @Override
    public void insertOption(String name, String value) {

    }

    @Override
    public List<OptionVo> getOptions() {
        return null;
    }

    @Override
    public void saveOptions(Map<String, String> options) {

    }

    @Override
    public OptionVo getOptionByName(String name) {
        return null;
    }
}
