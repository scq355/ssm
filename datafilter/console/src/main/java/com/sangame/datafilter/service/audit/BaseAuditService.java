package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.activeMq.comment.CommentResponseProducer;
import com.sangame.datafilter.activeMq.investment.InvestmentResponseProducer;
import com.sangame.datafilter.activeMq.jiepan.JiepanResponseProducer;
import com.sangame.datafilter.activeMq.market.MarketResponseProducer;
import com.sangame.datafilter.activeMq.quiz.QuizResponseProducer;
import com.sangame.datafilter.common.constant.CommonConstant;
import com.sangame.datafilter.common.persistence.mapper.audit.*;
import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.common.persistence.model.FilterWhiteUser;
import com.sangame.datafilter.common.persistence.model.audit.BaseAuditModel;
import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.util.PackageMQResult;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.service.*;
import com.sangame.datafilter.util.AuditUtil;
import com.sangame.datafilter.util.ConsoleUtil;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Mao Renwei
 * @Description 审核通用的处理方式
 * @date 2017/5/8.
 */
@Service
public class BaseAuditService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseAuditService.class);

    @Autowired
    protected FilterCommentAuditMapper filterCommentAuditMapper;
    @Autowired
    protected CommentResponseProducer commentResponseProducer;
    @Autowired
    protected FilterInvestmentAuditMapper filterInvestmentAuditMapper;
    @Autowired
    protected InvestmentResponseProducer investmentResponseProducer;
    @Autowired
    protected FilterJiepanAuditMapper filterJiepanAuditMapper;
    @Autowired
    protected JiepanResponseProducer jiepanResponseProducer;
    @Autowired
    protected FilterMarketAuditMapper filterMarketAuditMapper;
    @Autowired
    protected MarketResponseProducer marketResponseProducer;
    @Autowired
    protected FilterQuizAuditMapper filterQuizAuditMapper;
    @Autowired
    protected QuizResponseProducer quizResponseProducer;

    @Autowired
    private FilterAuditLogMapper filterAuditLogMapper;
    @Autowired
    private FilterWhiteUserService filterWhiteUserService;
    @Autowired
    private FilterBlackUserService filterBlackUserService;
    @Autowired
    private FilterVocabularyService filterVocabularyService;
    @Autowired
    private FilterSourceService filterSourceService;
    @Autowired
    private SysConfigService sysConfigService;


    //插入数据的时候，数据的状态除了  待审核，其他都需要在 log 表中插入一条数据
    public String insert(BaseAuditMapper baseAuditMapper, BaseAuditModel baseAuditModel){
        FilterBlackUser blackUser = AuditUtil.getBlackUserFromAudit(baseAuditModel);
        //处理相关的业务逻辑：黑名单，白名单等的判断，当违反规则的时候判断下是否，超过配置了，是的话，将用户设置为冻结，黑名单。
        boolean isBlackUser = filterBlackUserService.isBlackUserByUserIdAndIp(baseAuditModel.getUserId(),baseAuditModel.getIp());

        //判断黑名单
        if(isBlackUser){
            baseAuditModel.setStatus(CommonConstant.DataStatus.REJECT.getValue());
            baseAuditMapper.insert(baseAuditModel);

            String result = PackageMQResult.packageWithCommentAudit(baseAuditModel);
            return result;
        }

        String originalData = baseAuditModel.getOriginalData();

        //判断非法词
        Set<String> illegalWords = filterVocabularyService.getSensitiveWord(originalData, ConsoleConstant.VocabularyType.ILLEGAL.getValue());
        if(illegalWords != null && illegalWords.size() > 0){


            blackUser.setBlackReason("非法词");
            sysConfigService.isBeyondMaxIllegal(blackUser);
            baseAuditModel.setStatus(CommonConstant.DataStatus.REJECT.getValue());

            String filterData = originalData;
            //取出非法词，将非法词进行替换，
            for(String illegalWord : illegalWords){
                filterData = originalData.replace(illegalWord,"<span class='illeagelWord'>"+illegalWord+"</span>");
                originalData = filterData;
            }
            baseAuditModel.setFilterData(filterData);
            baseAuditMapper.insert(baseAuditModel);

            String result = PackageMQResult.packageWithCommentAudit(baseAuditModel);
            return result;
        }

        //判断白名单
        FilterWhiteUser whiteUser = AuditUtil.getWhiteUserFromAudit(baseAuditModel);
        boolean isWhiteUser = filterWhiteUserService.isWhiteUserByUserIdOrIp(whiteUser);
        if(isWhiteUser){
            baseAuditModel.setStatus(CommonConstant.DataStatus.SYS_PASS.getValue());
            baseAuditMapper.insert(baseAuditModel);

            String result = PackageMQResult.packageWithCommentAudit(baseAuditModel);
            return result;
        }

        //判断敏感词
        Set<String> sensitiveWords = filterVocabularyService.getSensitiveWord(baseAuditModel.getOriginalData(),ConsoleConstant.VocabularyType.SENSITIVE.getValue());
        if(sensitiveWords != null && sensitiveWords.size() > 0){

            blackUser.setBlackReason("敏感词");
            sysConfigService.isBeyondMaxSensitiveWord(blackUser);

            baseAuditModel.setStatus(CommonConstant.DataStatus.CHECK.getValue());

            //取出敏感词，进行替换，
            String filterData = originalData;
            for(String sensitiveWord : sensitiveWords){
                filterData = originalData.replace(sensitiveWord,"<span class='sensitiveWord'>"+sensitiveWord+"</span>");
                originalData = filterData;
            }
            baseAuditModel.setFilterData(filterData);

            baseAuditMapper.insert(baseAuditModel);
            //待审核
            return null;
        }

        //判断审核规则
        int auditType = filterSourceService.getAuditRuleByKey(ConsoleConstant.ProjectType.COMMENT.getKeyWord());
        if(auditType == ConsoleConstant.AuditRuleType.SEND_FIRST.getValue()){
            baseAuditModel.setStatus(CommonConstant.DataStatus.SYS_PASS.getValue());
            baseAuditMapper.insert(baseAuditModel);

            sysConfigService.isBeyondMaxSubmit(blackUser);

            String result = PackageMQResult.packageWithCommentAudit(baseAuditModel);
            return result;
        }else if(auditType == ConsoleConstant.AuditRuleType.CHECK_FIRST.getValue()){
            baseAuditModel.setStatus(CommonConstant.DataStatus.CHECK.getValue());
            baseAuditMapper.insert(baseAuditModel);

            sysConfigService.isBeyondMaxSubmit(blackUser);
            //待审核
            return null;
        }else{
            return null;
        }
    }


    /**
     * @author MaoRenwei
     * @description 单条处理 状态数据
     * @date 2017/5/5
     */
    public String updateStatus(BaseAuditMapper baseAuditMapper,int status,Long id, FilterAuditLog auditLog) {

        baseAuditMapper.updateDataStatus(status,id);

        auditLog.setCreatedAt(new Date());
        auditLog.setType(ConsoleConstant.ProjectType.COMMENT.getValue());
        auditLog.setAuditId(id);
        if(status == CommonConstant.DataStatus.SYS_PASS.getValue()){
            auditLog.setCreator("系统");
        }else{
            auditLog.setCreator(ConsoleUtil.getCurAdminName()); //插入创建者
        }

        //同时插入 记录日志的数据
        filterAuditLogMapper.insert(auditLog);

        //同时将消息发送出去
        String result = PackageMQResult.packageWithIdAndStatus(id,status);
        return result;
    }

    /**
     * @author MaoRenwei
     * @description 批量处理 状态数据
     * @date 2017/5/5
     */
    public String batchUpdateStatus(BaseAuditMapper baseAuditMapper,int status,String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{

        List<FilterAuditLog> auditLogs = new ArrayList<FilterAuditLog>();

        List<Long> idsList = new ArrayList<Long>();
        String[] idArr = ids.split(",");
        baseAuditMapper.BatchUpdateDataStatus(status,idArr);

        auditLog.setCreatedAt(new Date());
        auditLog.setType(ConsoleConstant.ProjectType.COMMENT.getValue());
        if(status == CommonConstant.DataStatus.SYS_PASS.getValue()){
            auditLog.setCreator("系统");
        }else{
            auditLog.setCreator(ConsoleUtil.getCurAdminName()); //插入创建者
        }

        for(int i = 0; i < idArr.length; i++){
            Long idTemp = Long.parseLong(idArr[i]);
            FilterAuditLog auditLogClone = (FilterAuditLog) auditLog.clone();
            auditLogClone.setAuditId(idTemp);
            auditLogs.add(auditLogClone);
            idsList.add(idTemp);
        }

        //同时插入 记录日志的数据
        filterAuditLogMapper.batchInsert(auditLogs);

        //同时将消息发送出去
        String result = PackageMQResult.packageWithIdsAndStatus(idsList,status);
        return result;
    }

    /**
     * @author MaoRenwei
     * @description 查询功能。通用的查询
     * @date 2017/4/26
     */
    public Render searchData(BaseAuditMapper baseAuditMapper,BaseAuditModel baseAuditModel){

        List<T> resultData = baseAuditMapper.searchDatas(baseAuditModel);
        int totalCount = baseAuditMapper.searchDatasCount(baseAuditModel);

        Render render = new Render(true,"获取数据列表成功",totalCount,resultData);
        return render;
    }

    /**
     * @author MaoRenwei
     * @description 用户冻结之后,判断是否 处理历史数据
     * @date 2017/5/3
     */
    public String dealHistoryData(BaseAuditMapper baseAuditMapper,boolean dealDataFlag,String userIds,String auditIds){

        if(dealDataFlag){
            if(userIds != null){
                String[] idArra = userIds.split(",");
                // 通过userId,查找出所有数据设置为拒绝，返回ids,
                baseAuditMapper.updateDtatStatusByUserIdWithStatus(idArra,CommonConstant.DataStatus.REJECT.getValue());
                List<Long> ids = baseAuditMapper.getIdsByUserId(idArra);

                String result = PackageMQResult.packageWithIdsAndStatus(ids,CommonConstant.DataStatus.REJECT.getValue());
                return result;
            }
        }else{
            //处理相关数据
            if(auditIds != null){
                String[] auditIdsArra = auditIds.split(",");
                List<Long> ids = new ArrayList<Long>();
                baseAuditMapper.BatchUpdateDataStatus(CommonConstant.DataStatus.REJECT.getValue(),auditIdsArra);
                for(int i = 0; i < auditIdsArra.length; i++){
                    ids.add(Long.parseLong(auditIdsArra[i]));
                }
                String result = PackageMQResult.packageWithIdsAndStatus(ids,CommonConstant.DataStatus.REJECT.getValue());
                return result;
            }
        }
        return null;
    }

    /**
     * @author MaoRenwei
     * @description 通过userId 查找出这个用户的所有数据
     * @date 2017/5/3
     */
    public List<T> getDataByUserId(BaseAuditMapper baseAuditMapper,int userId){
        List<T> result = baseAuditMapper.getDataByUserId(userId);
        return result;
    }

    /**
    * @author MaoRenwei
    * @description 根据状态查找数据
    * @date 2017/5/10
    */
    public int getDatasCountByStatus(BaseAuditMapper baseAuditMapper,int status){
        int count = baseAuditMapper.getDatasCountByStatus(status);
        return count;
    }
}
