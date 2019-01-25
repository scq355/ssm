package com.sangame.datafilter.service.audit;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.activeMq.comment.CommentResponseProducer;
import com.sangame.datafilter.activeMq.investment.InvestmentResponseProducer;
import com.sangame.datafilter.activeMq.jiepan.JiepanResponseProducer;
import com.sangame.datafilter.activeMq.market.MarketResponseProducer;
import com.sangame.datafilter.activeMq.quiz.QuizResponseProducer;
import com.sangame.datafilter.common.persistence.mapper.audit.*;
import com.sangame.datafilter.common.persistence.model.audit.*;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.util.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  
public class FilterDataExceptionService {
	
	@Autowired
    private FilterDataExceptionMapper filterDataExceptionMapper;

	@Autowired
	private BaseAuditService baseAuditService;
	@Autowired
	private FilterCommentAuditMapper filterCommentAuditMapper;
	@Autowired
	private FilterInvestmentAuditMapper filterInvestmentAuditMapper;
	@Autowired
	private FilterMarketAuditMapper filterMarketAuditMapper;
	@Autowired
	private FilterQuizAuditMapper filterQuizAuditMapper;
	@Autowired
	private FilterJiepanAuditMapper filterJiepanAuditMapper;
	@Autowired
	private CommentResponseProducer commentResponseProducer;
	@Autowired
	private InvestmentResponseProducer investmentResponseProducer;
	@Autowired
	private QuizResponseProducer quizResponseProducer;
	@Autowired
	private JiepanResponseProducer jiepanResponseProducer;
	@Autowired
	private MarketResponseProducer marketResponseProducer;
    
   	public void updateStatus(FilterDataException dataException){

		int projectType = dataException.getProjectType();
		int dealType = dataException.getDealType();
		if(dealType == ConsoleConstant.ExceptionDealType.SEND.getValue()){
			//发送
			String msg = dataException.getJson();

			if(projectType == ConsoleConstant.ProjectType.COMMENT.getValue()){
				commentResponseProducer.sendMessage(msg,false);
			}else if(projectType == ConsoleConstant.ProjectType.INVESTMENT.getValue()){
				investmentResponseProducer.sendMessage(msg,false);
			}else if(projectType == ConsoleConstant.ProjectType.QUIZ.getValue()){
				quizResponseProducer.sendMessage(msg,false);
			}else if(projectType == ConsoleConstant.ProjectType.JIEPAN.getValue()){
				jiepanResponseProducer.sendMessage(msg,false);
			}else if(projectType == ConsoleConstant.ProjectType.MARKET.getValue()){
				marketResponseProducer.sendMessage(msg,false);
			}

		}else{
			//插入
			String json = dataException.getJson();

			if(projectType == ConsoleConstant.ProjectType.COMMENT.getValue()){
				FilterCommentAudit commentAudit = JSON.parseObject(json,FilterCommentAudit.class);
				baseAuditService.insert(filterCommentAuditMapper,commentAudit);
			}else if(projectType == ConsoleConstant.ProjectType.INVESTMENT.getValue()){
				FilterInvestmentAudit investmentAudit = JSON.parseObject(json,FilterInvestmentAudit.class);
				baseAuditService.insert(filterInvestmentAuditMapper,investmentAudit);
			}else if(projectType == ConsoleConstant.ProjectType.QUIZ.getValue()){
				FilterQuizAudit filterQuizAudit = JSON.parseObject(json,FilterQuizAudit.class);
				baseAuditService.insert(filterQuizAuditMapper,filterQuizAudit);
			}else if(projectType == ConsoleConstant.ProjectType.JIEPAN.getValue()){
				FilterJiepanAudit filterJiepanAudit = JSON.parseObject(json,FilterJiepanAudit.class);
				baseAuditService.insert(filterJiepanAuditMapper,filterJiepanAudit);
			}else if(projectType == ConsoleConstant.ProjectType.MARKET.getValue()){
				FilterMarketAudit filterMarketAudit = JSON.parseObject(json,FilterMarketAudit.class);
				baseAuditService.insert(filterMarketAuditMapper,filterMarketAudit);
			}
		}

		filterDataExceptionMapper.updateStatus(dataException);
	}

	public void insert(FilterDataException dataException){
		filterDataExceptionMapper.insert(dataException);
	}

	public Render searchDatas(FilterDataException dataExceprion){
		List<FilterDataException> results = filterDataExceptionMapper.searchDatas(dataExceprion);
		int totalCount = filterDataExceptionMapper.searchDatasCount(dataExceprion);
		Render render = new Render(true,"获取数据列表成功",totalCount,results);
		return render;
	}
	
}
