package com.example.cloud.gatewayclient.pojo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CfgSequence implements Serializable {
    private String seqName;
    private String appCode;
    private Integer minVal;
    private Integer maxVal;
    private Integer curVal;
    private Date updateTime;
    private String remark;
    private List<String> whereList;
    private List<String> updateList;
    private static final long serialVersionUID = 1L;

    public CfgSequence() {
    }

    public String getSeqName() {
        return this.seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getMinVal() {
        return this.minVal;
    }

    public void setMinVal(Integer minVal) {
        this.minVal = minVal;
    }

    public Integer getMaxVal() {
        return this.maxVal;
    }

    public void setMaxVal(Integer maxVal) {
        this.maxVal = maxVal;
    }

    public Integer getCurVal() {
        return this.curVal;
    }

    public void setCurVal(Integer curVal) {
        this.curVal = curVal;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that == null) {
            return false;
        } else if (this.getClass() != that.getClass()) {
            return false;
        } else {
            boolean var10000;
            label89: {
                label81: {
                    CfgSequence other = (CfgSequence)that;
                    if (this.getSeqName() == null) {
                        if (other.getSeqName() != null) {
                            break label81;
                        }
                    } else if (!this.getSeqName().equals(other.getSeqName())) {
                        break label81;
                    }

                    if (this.getAppCode() == null) {
                        if (other.getAppCode() != null) {
                            break label81;
                        }
                    } else if (!this.getAppCode().equals(other.getAppCode())) {
                        break label81;
                    }

                    if (this.getMinVal() == null) {
                        if (other.getMinVal() != null) {
                            break label81;
                        }
                    } else if (!this.getMinVal().equals(other.getMinVal())) {
                        break label81;
                    }

                    if (this.getMaxVal() == null) {
                        if (other.getMaxVal() != null) {
                            break label81;
                        }
                    } else if (!this.getMaxVal().equals(other.getMaxVal())) {
                        break label81;
                    }

                    if (this.getCurVal() == null) {
                        if (other.getCurVal() != null) {
                            break label81;
                        }
                    } else if (!this.getCurVal().equals(other.getCurVal())) {
                        break label81;
                    }

                    if (this.getUpdateTime() == null) {
                        if (other.getUpdateTime() != null) {
                            break label81;
                        }
                    } else if (!this.getUpdateTime().equals(other.getUpdateTime())) {
                        break label81;
                    }

                    if (this.getRemark() == null) {
                        if (other.getRemark() == null) {
                            break label89;
                        }
                    } else if (this.getRemark().equals(other.getRemark())) {
                        break label89;
                    }
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        }
    }



    public List<String> getWhereList() {
        return this.whereList;
    }

    public List<String> setWhereList(List<String> whereList) {
        this.whereList = whereList;
        return this.whereList;
    }

    public List<String> getUpdateList() {
        return this.updateList;
    }

    public List<String> setUpdateList(List<String> updateList) {
        this.updateList = updateList;
        return this.updateList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", seqName=").append(this.seqName);
        sb.append(", appCode=").append(this.appCode);
        sb.append(", minVal=").append(this.minVal);
        sb.append(", maxVal=").append(this.maxVal);
        sb.append(", curVal=").append(this.curVal);
        sb.append(", updateTime=").append(this.updateTime);
        sb.append(", remark=").append(this.remark);
        sb.append(", whereList=").append(this.whereList);
        sb.append(", updateList=").append(this.updateList);
        sb.append("]");
        return sb.toString();
    }
}
