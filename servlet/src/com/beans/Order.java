package com.beans;

public class Order {
    private Integer id;
    private String msg;
    private Integer status;
    private Integer quid;
    private Integer fuid;
    private String createTime;
    private String qTime;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                ", quid=" + quid +
                ", fuid=" + fuid +
                ", createTime='" + createTime + '\'' +
                ", qTime='" + qTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuid() {
        return quid;
    }

    public void setQuid(Integer quid) {
        this.quid = quid;
    }

    public Integer getFuid() {
        return fuid;
    }

    public void setFuid(Integer fuid) {
        this.fuid = fuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getqTime() {
        return qTime;
    }

    public void setqTime(String qTime) {
        this.qTime = qTime;
    }
}
