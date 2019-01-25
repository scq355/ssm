package com.sangame.datafilter.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sangame.datafilter.common.persistence.model.FilterVocabulary;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant.deleteFlagType;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.FilterVocabularyService;
import com.sangame.datafilter.util.ExcelUtils;
import com.sangame.datafilter.util.Render;

@Controller
@RequestMapping(value="/vocabulary", method=RequestMethod.GET)
public class VocabularyController {

	private final static Logger Log = LoggerFactory.getLogger(VocabularyController.class);

	@Autowired
	private FilterVocabularyService vocabularyService;

	/**
	 * 进入index页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(ModelMap model) {
		return PageConstant.VOCABULARY_INDEX_PAGE;
	}

	/**
	 * 获取列表数据
	 * @param type
	 * @param content
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public Render list(
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "contentLike", required = false) String contentLike,
			@RequestParam(value = "pageStart", required = false, defaultValue = "1") Integer pageStart,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("deleteFlag", deleteFlagType.FALSE.getValue());
		if (type != null && type > 0) parm.put("type", type);
		if (StringUtils.isNotBlank(contentLike)) parm.put("contentLike", contentLike);
		PageUtil page = vocabularyService.getListByParm(parm, pageStart, pageSize);
		return new Render(true, "获取列表数据成功！", page.getTotalRecords(), page.getList());
	}

	/**
	 * 进入新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add.do", method=RequestMethod.GET)
	public String add(ModelMap model) {
		return PageConstant.VOCABULARY_ADD_PAGE;
	}

	/**
	 * 创建新数据
	 * @param content
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/create.do", method=RequestMethod.GET)
	public Render create(
			@RequestParam(value = "content", required = true) String[] content,
			@RequestParam(value = "type", required = true) Integer type) {
		if (content.length < 1)
			return new Render(false, "参数有误！");
		for (int i=0; i<content.length; i++) {
			//如果新增的过滤词已存在，则跳过
			if (vocabularyService.isExistVocabulary(content[i],type)) {
				continue;
			}
			FilterVocabulary filterVocabulary = new FilterVocabulary();
			filterVocabulary.setContent(content[i]);
			filterVocabulary.setType(type);
			filterVocabulary.setDeleteFlag(deleteFlagType.FALSE.getValue());
			vocabularyService.insert(filterVocabulary);
		}
		return new Render(true, "创建成功！");
	}

	/**
	 * 进入修改页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String edit(Model model,
			@RequestParam(value = "id", required = true) Long id) {
		FilterVocabulary vocabulary = vocabularyService.getById(id);
		model.addAttribute("vocabulary", vocabulary);
		return PageConstant.VOCABULARY_EDIT_PAGE;
	}

	/**
	 * 修改保存数据
	 * @param id
	 * @param content
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public Render update(
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "type", required = true) Integer type) {
		//如果新增的过滤词已存在，则跳过
		if (vocabularyService.isExistVocabulary(content,type)) {
			return new Render(false, "该过滤已存在！");
		}
		FilterVocabulary filterVocabulary = new FilterVocabulary();
		filterVocabulary.setId(id);
		filterVocabulary.setContent(content);
		filterVocabulary.setType(type);
		filterVocabulary.setDeleteFlag(deleteFlagType.FALSE.getValue());
		filterVocabulary.setModifier("");//修改人设置为空，拦截器进行自动处理
		vocabularyService.update(filterVocabulary);
		return new Render(true, "更新成功！");
	}

	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public Render delete(@RequestParam(value = "id", required = true) Long id) {
		vocabularyService.delete(id);
		return new Render(true, "删除成功！");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batchDelete.do", method=RequestMethod.GET)
	public Render batchDelete(@RequestParam(value = "ids", required = true) String ids) {
		String[] idArray = ids.split(",");
		vocabularyService.batchDelete(idArray);
		return new Render(true, "批量删除成功！");
	}

	/**
	 * 批量更改类型，非法词/敏感词
	 * @param ids
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batchUpdate.do", method=RequestMethod.GET)
	public Render batchUpdate(@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "type", required = true) Integer type) {
		String[] idArray = ids.split(",");
		vocabularyService.batchUpdate(idArray, type);
		return new Render(true, "批量更改类型成功！");
	}

	/**
	 * 进入批量新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/batchAdd.do", method=RequestMethod.GET)
	public String batchAdd(ModelMap model) {
		return PageConstant.VOCABULARY_BATCH_ADD_PAGE;
	}

	@ResponseBody
	@RequestMapping(value="/upExcelBatchAddData.do", method=RequestMethod.POST)
	public Render upExcelBatchAddData(@RequestParam(value = "file", required = true) MultipartFile file) {
		int i = 1;
		try {
			if (file.getSize() == 0) {
	            return new Render(false, "文件内容为空或不存在");
	        }
	        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
	        if (!("xls".equals(fileExtension))){
	            return new Render(false, "文件类型错误");
	        }
	        
	        //转存文件
	        File tempfile = new File(System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis() + file.getOriginalFilename());  
	        file.transferTo(tempfile);
	        //读取文件数据
	        ArrayList<ArrayList<Object>> rowList = ExcelUtils.readExcel2003(tempfile);
	        //第一行为标题，所以从第2行开始取数据
	        if (rowList.size() < 2) return new Render(false, "读取EXCEL文件，未取到数据！");
	        List<FilterVocabulary> vocabularyList = new ArrayList<FilterVocabulary>();
	        for (; i<rowList.size(); i++) {
	        	if (rowList.get(i).size() < 2) return new Render(false, "读取EXCEL文件，第" + (i + 1) + "行数据异常！");
	            String content = (String) rowList.get(i).get(0);
	            String typeStr = (String) rowList.get(i).get(1);
	            int type = Double.valueOf(typeStr.trim()).intValue();
	            //判断是否已存在
	            if (vocabularyService.isExistVocabulary(content,type)) {
	            	continue;
	            }
	            //新增
	            FilterVocabulary filterVocabulary = new FilterVocabulary();
				filterVocabulary.setContent(content);
				filterVocabulary.setType(type);
				filterVocabulary.setDeleteFlag(deleteFlagType.FALSE.getValue());
				vocabularyList.add(filterVocabulary);
				
	        }
	        if (vocabularyList.size() > 0)
	        	vocabularyService.batchInsert(vocabularyList);
			return new Render(true, "通过上传Excel批量添加数据成功！");
        } catch (Exception e) {
        	Log.error("通过上传Excel批量添加数据失败！", e);
        	return new Render(false, "通过上传Excel批量添加数据失败！在添加到第" + i + "条数据时报错，错误信息：" + e);
        }
	}
	
}
