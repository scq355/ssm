package com.sangame.datafilter.common.persistence.mapper.audit;

import com.sangame.datafilter.common.persistence.model.audit.BaseAuditModel;
import com.sangame.datafilter.common.persistence.model.pandect.DataCountStatistic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/5/2.
 */
@Repository
public interface BaseAuditMapper<T> {
    int getDatasCountByStatus(@Param("status") int status);

    int getTodayDatasCountByStatus(@Param("status") int status);

    List<DataCountStatistic> getDatasCountStatistic(@Param("status") int status);

    void insert(T baseAuditModel);

    void updateDataStatus(@Param("status") int status,@Param("id")Long id);

    void BatchUpdateDataStatus(@Param("status") int status,@Param("ids")String[] ids);

    List<T> searchDatas(T baseAuditModel);

    int searchDatasCount(T baseAuditModel);

    void updateDtatStatusByUserIdWithStatus(@Param("userIds")String[] userIds ,@Param("status")int status);

    List<Long> getIdsByUserId(@Param("userIds")String[] userIds);

    List<T> getDataByUserId(@Param("userId")int userId);
}
