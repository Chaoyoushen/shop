package org.yufan.common;

import java.io.Serializable;

public class Result<T> implements Serializable {


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 返回的数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
