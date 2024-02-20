package cn.wolfcode.business.manage.credit.domain;

import cn.wolfcode.common.annotation.Excel;
import cn.wolfcode.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 机构异常信息对象 cel_org_credit
 * 
 * @author Bycor
 * @date 2024-02-20
 */
public class CelOrgCredit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 版本号 */
    private Long version;

    /** 创建人id */
    private Long createdBy;

    /** 创建人名字 */
    private String createdByName;

    /** 创建时间 */
    private Date createdTime;

    /** 更新人id */
    private Long updatedBy;

    /** 更新人名字 */
    private String updatedByName;

    /** 更新时间*/
    private Date updatedTime;

    /**  */
    private Integer deleted;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 事件时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eventData;

    /** 事件类型 */
    private String eventType;

    /** 事件描述 */
    @Excel(name = "事件描述")
    private String eventDescribe;

    /** 来源类型【预留字段，以后会有其他数据来源】 */
    private Long sourceType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setCreatedBy(Long createdBy) 
    {
        this.createdBy = createdBy;
    }

    public Long getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedByName(String createdByName) 
    {
        this.createdByName = createdByName;
    }

    public String getCreatedByName() 
    {
        return createdByName;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(Long updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedByName(String updatedByName) 
    {
        this.updatedByName = updatedByName;
    }

    public String getUpdatedByName() 
    {
        return updatedByName;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }
    public void setOrgId(Long orgId) 
    {
        this.orgId = orgId;
    }

    public Long getOrgId() 
    {
        return orgId;
    }
    public void setEventData(Date eventData) 
    {
        this.eventData = eventData;
    }

    public Date getEventData() 
    {
        return eventData;
    }
    public void setEventType(String eventType) 
    {
        this.eventType = eventType;
    }

    public String getEventType() 
    {
        return eventType;
    }
    public void setEventDescribe(String eventDescribe) 
    {
        this.eventDescribe = eventDescribe;
    }

    public String getEventDescribe() 
    {
        return eventDescribe;
    }
    public void setSourceType(Long sourceType) 
    {
        this.sourceType = sourceType;
    }

    public Long getSourceType() 
    {
        return sourceType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("createdBy", getCreatedBy())
            .append("createdByName", getCreatedByName())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedByName", getUpdatedByName())
            .append("updatedTime", getUpdatedTime())
            .append("deleted", getDeleted())
            .append("orgId", getOrgId())
            .append("eventData", getEventData())
            .append("eventType", getEventType())
            .append("eventDescribe", getEventDescribe())
            .append("sourceType", getSourceType())
            .toString();
    }
}
