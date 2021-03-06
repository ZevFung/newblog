package com.myblog.service.impl;

import com.myblog.dao.BlogMapper;
import com.myblog.dao.IpLogMapper;
import com.myblog.model.IpLog;
import com.myblog.service.IAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/26 18:32
 * Description:Spring中异步
 */
@Service
@Transactional
public class AsyncServiceImpl implements IAsyncService {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Resource
    private IpLogMapper ipLogMapper;
    @Resource
    private BlogMapper blogMapper;

    @Async
    @Override
    public void insertIpLog(IpLog ipLog) {
        try {
            ipLogMapper.insertSelective(ipLog);      //记录每一条日志
        } catch (Exception e) {
            logger.error("ip插入错误", e);
        }
    }

    @Async
    @Override
    public void updatebloghits(Integer blogid) {
        try{
            blogMapper.updatehits(blogid);
        }catch (Exception e){
            logger.error("更新阅读次数错误",e);
        }
    }
}