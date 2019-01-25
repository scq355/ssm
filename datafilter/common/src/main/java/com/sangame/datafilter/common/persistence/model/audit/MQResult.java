package com.sangame.datafilter.common.persistence.model.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mao Renwei
 * @Description mq 返回的数据。
 * @date 2017/5/4.
 */
public class MQResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> ids;
    private int status;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(Long id){
        if(this.ids == null){
            this.ids = new ArrayList<Long>();
        }
        ids.add(id);
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
