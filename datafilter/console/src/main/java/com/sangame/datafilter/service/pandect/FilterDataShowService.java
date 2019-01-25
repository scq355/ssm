package com.sangame.datafilter.service.pandect;



import com.sangame.datafilter.common.constant.CommonConstant;
import com.sangame.datafilter.common.persistence.mapper.audit.*;
import com.sangame.datafilter.common.persistence.model.pandect.DataCount;
import com.sangame.datafilter.common.persistence.model.pandect.DataCountStatistic;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.redis.RedisCacheKey;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/4/26.
 */
@Service
public class FilterDataShowService {

    private static final Logger LOG = LoggerFactory.getLogger(FilterDataShowService.class);
    private static final int TOTAY_DAYS = 10;

    @Autowired
    private FilterCommentAuditMapper filterCommentAuditMapper;
    @Autowired
    private FilterInvestmentAuditMapper filterInvestmentAuditMapper;
    @Autowired
    private FilterJiepanAuditMapper filterJiepanAuditMapper;
    @Autowired
    private FilterMarketAuditMapper filterMarketAuditMapper;
    @Autowired
    private FilterQuizAuditMapper filterQuizAuditMapper;
    @Autowired
    FilterAuditLogMapper filterAuditLogMapper;


    /**
    * @author MaoRenwei
    * @description 获取数据总览显示的数据
    * @date 2017/5/2
    */
    public List<DataCount> getDataCount() {

        //先判断缓存中是否存在，
        List<DataCount> result =(List<DataCount>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT);
        if(result == null){
            result = new ArrayList<DataCount>();
            DataCount totalDataCount = new DataCount();        //总况
            totalDataCount.setProjectName(ConsoleConstant.ProjectType.TOTAL.getName());


            DataCount commentDataCount = getDataCountByProjectType(filterCommentAuditMapper,totalDataCount);
            commentDataCount.setProjectName(ConsoleConstant.ProjectType.COMMENT.getName());


            DataCount investmentDataCount = getDataCountByProjectType(filterInvestmentAuditMapper,totalDataCount);
            investmentDataCount.setProjectName(ConsoleConstant.ProjectType.INVESTMENT.getName());


            DataCount jiepanDataCount = getDataCountByProjectType(filterJiepanAuditMapper,totalDataCount);
            jiepanDataCount.setProjectName(ConsoleConstant.ProjectType.JIEPAN.getName());


            DataCount marketDataCount = getDataCountByProjectType(filterMarketAuditMapper,totalDataCount);
            marketDataCount.setProjectName(ConsoleConstant.ProjectType.MARKET.getName());


            DataCount quizDataCount = getDataCountByProjectType(filterQuizAuditMapper,totalDataCount);
            quizDataCount.setProjectName(ConsoleConstant.ProjectType.QUIZ.getName());


            result.add(totalDataCount);
            result.add(commentDataCount);
            result.add(investmentDataCount);
            result.add(jiepanDataCount);
            result.add(marketDataCount);
            result.add(quizDataCount);

            RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT,result, RedisCacheTime.FIVE_MINUTE_STORE);
        }
        return result;
    }


    /**
    * @author MaoRenwei
    * @description 获取数据
    * @date 2017/5/2
    */
    private DataCount getDataCountByProjectType(BaseAuditMapper baseAuditMapper,DataCount totalDataCount){
        DataCount dataCount = new DataCount();
        //待审核，所有未审核的数据
        int checkCount = baseAuditMapper.getDatasCountByStatus(CommonConstant.DataStatus.CHECK.getValue());

        // 今天系统和人工通过的数据。
        int passManualCount = baseAuditMapper.getTodayDatasCountByStatus(CommonConstant.DataStatus.MANUAL_PASS.getValue());
        int passSysCount = baseAuditMapper.getTodayDatasCountByStatus(CommonConstant.DataStatus.SYS_PASS.getValue());
        //今天所有的评论数
        int participationCount = baseAuditMapper.getTodayDatasCountByStatus(CommonConstant.DataStatus.ALL.getValue());

        int total = baseAuditMapper.getDatasCountByStatus(CommonConstant.DataStatus.ALL.getValue());

        dataCount.setCheckCount(checkCount);
        dataCount.setPassManualCount(passManualCount);
        dataCount.setPassSysCount(passSysCount);
        dataCount.setParticipationCount(participationCount);
        dataCount.setTotalCount(total);

        //总况数据
        totalDataCount.setCheckCount(totalDataCount.getCheckCount() + checkCount);
        totalDataCount.setPassManualCount(totalDataCount.getPassManualCount() + passManualCount);
        totalDataCount.setPassSysCount(totalDataCount.getPassSysCount() + passSysCount);
        totalDataCount.setParticipationCount(totalDataCount.getParticipationCount() + participationCount);
        totalDataCount.setTotalCount(totalDataCount.getTotalCount() + total);
        return dataCount;
    }

    /**
    * @author MaoRenwei
    * @description 数据总览当中的  图片当中的数据。
    * @date 2017/5/2
    */
    public  List<DataCountStatistic> getDataCountStatistic(int projectType,int dataStatus){

        List<DataCountStatistic> result = null;
        Map<String,Integer> map = tenDaysMap();
        if(projectType == ConsoleConstant.ProjectType.COMMENT.getValue()){
            result =(List<DataCountStatistic>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT_STATIC+"comment");
            if(result == null || result.size() == 0){
                result=getDataCountStatisticByStatus(dataStatus,filterCommentAuditMapper);
                assambleTotal(map ,result);
                result = mapTransformToList(map);
                RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT_STATIC+"comment",result,RedisCacheTime.ONE_HOUR_STORE);
            }
        }else if(projectType == ConsoleConstant.ProjectType.INVESTMENT.getValue()){
            result =(List<DataCountStatistic>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT_STATIC+"investment");
            if(result == null || result.size() == 0){
                result=getDataCountStatisticByStatus(dataStatus,filterInvestmentAuditMapper);
                assambleTotal(map ,result);
                result = mapTransformToList(map);
                RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT_STATIC+"investment",result,RedisCacheTime.ONE_HOUR_STORE);
            }
        }else if(projectType == ConsoleConstant.ProjectType.JIEPAN.getValue()){
            result =(List<DataCountStatistic>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT_STATIC+"jiepan");
            if(result == null || result.size() == 0){
                result=getDataCountStatisticByStatus(dataStatus,filterJiepanAuditMapper);
                assambleTotal(map ,result);
                result = mapTransformToList(map);
                RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT_STATIC+"jiepan",result,RedisCacheTime.ONE_HOUR_STORE);
            }
        }else if(projectType == ConsoleConstant.ProjectType.QUIZ.getValue()){
            result =(List<DataCountStatistic>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT_STATIC+"quiz");
            if(result == null || result.size() == 0){
                result=getDataCountStatisticByStatus(dataStatus,filterQuizAuditMapper);
                assambleTotal(map ,result);
                result = mapTransformToList(map);
                RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT_STATIC+"quiz",result,RedisCacheTime.ONE_HOUR_STORE);
            }
        }else if(projectType == ConsoleConstant.ProjectType.MARKET.getValue()){
            result =(List<DataCountStatistic>) RedisHelper.getObjectByDeserialize(RedisCacheKey.DATA_PANDECT_STATIC+"market");
            if(result == null || result.size() == 0){
                result=getDataCountStatisticByStatus(dataStatus,filterMarketAuditMapper);
                assambleTotal(map ,result);
                result = mapTransformToList(map);
                RedisHelper.setObjectBySerialize(RedisCacheKey.DATA_PANDECT_STATIC+"market",result,RedisCacheTime.ONE_HOUR_STORE);
            }
        }else if(projectType == ConsoleConstant.ProjectType.TOTAL.getValue()){

            //总况 需要获取其他内容的数据进行拼接
            result=getDataCountStatisticByStatus(dataStatus,filterCommentAuditMapper);
            assambleTotal(map ,result);
            result=getDataCountStatisticByStatus(dataStatus,filterInvestmentAuditMapper);
            assambleTotal(map ,result);
            result=getDataCountStatisticByStatus(dataStatus,filterJiepanAuditMapper);
            assambleTotal(map ,result);
            result=getDataCountStatisticByStatus(dataStatus,filterQuizAuditMapper);
            assambleTotal(map ,result);
            result=getDataCountStatisticByStatus(dataStatus,filterMarketAuditMapper);
            assambleTotal(map ,result);

            result = mapTransformToList(map);
        }
        return result;
    }

    private List<DataCountStatistic> getDataCountStatisticByStatus(int dataStatus,BaseAuditMapper baseAuditMapper){
        List<DataCountStatistic> result =  baseAuditMapper.getDatasCountStatistic(dataStatus);
        return result;
    }

    /**
    * @author MaoRenwei
    * @description 存储10天的日期
    * @date 2017/5/16
    */
    private Map<String,Integer> tenDaysMap(){
        Map<String,Integer> map = new HashMap<String,Integer>();
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
        Calendar today = Calendar.getInstance();
        map.put(smf.format(today.getTime()),0);

        for(int i = 1; i < TOTAY_DAYS; i++){
            today.add(Calendar.DAY_OF_MONTH ,-1);
            map.put(smf.format(today.getTime()),0);
        }
        return map;
    }

    /**
    * @author MaoRenwei
    * @description
    * @date 2017/5/16
    */
    public Map<String,Integer>  assambleTotal(Map<String,Integer> map,List<DataCountStatistic> list){

        for(int i = 0; i < list.size(); i++){
            DataCountStatistic dataCountStatistic = list.get(i);
            int count = map.get(dataCountStatistic.getDateTime());
            count = count + dataCountStatistic.getCount();
            map.put(dataCountStatistic.getDateTime(),count);
        }
        return map;
    }

    /**
    * @author MaoRenwei
    * @description 将map 转化为list
    * @date 2017/5/17
    */
    private List<DataCountStatistic> mapTransformToList(Map<String,Integer> map){
        List<DataCountStatistic> result = new ArrayList<DataCountStatistic>();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            DataCountStatistic dataCountStatistic = new DataCountStatistic();
            Map.Entry entry = (Map.Entry) it.next();
            dataCountStatistic.setDateTime((String) entry.getKey());
            dataCountStatistic.setCount((Integer) entry.getValue());
            result.add(dataCountStatistic);
        }

        Collections.sort(result, new Comparator<DataCountStatistic>() {

            public int compare(DataCountStatistic o1, DataCountStatistic o2) {
                return Integer.parseInt(o1.getDateTime()) - Integer.parseInt(o2.getDateTime());
            }
        });
        return result;
    }
}
