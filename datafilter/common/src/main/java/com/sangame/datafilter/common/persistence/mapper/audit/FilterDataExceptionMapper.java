package com.sangame.datafilter.common.persistence.mapper.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterDataExceptionMapper{

    void insert(FilterDataException dataException);

    void updateStatus(FilterDataException dataException);

    List<FilterDataException> searchDatas(FilterDataException dataException);

    int searchDatasCount(FilterDataException dataException);
}
