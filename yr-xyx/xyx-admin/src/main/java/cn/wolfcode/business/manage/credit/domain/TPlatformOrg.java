package cn.wolfcode.business.manage.credit.domain;

import cn.wolfcode.common.annotation.Excel;
import cn.wolfcode.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构基础信息 对象 t_platform_org_list
 * 
 * @author ruoyi
 * @date 2024-02-20
 */
public class TPlatformOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 版本 */
    private Long version;

    /** 创建人ID */
    private Long createdBy;

    /** 创建人姓名 */
    private String createdByName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人ID */
    private Long updatedBy;

    /** 更新人姓名 */
    private String updatedByName;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    /** 是否删除 */
    private Integer deleted;

    /** 人流量 */
    private String visitorsFlowRate;

    /** 信用分 */
    private Long creditScore;

    /** 信用评级：1:A+,2:A,3:B,4:C,5:D,6:E,7:F */
    private String creditRank;

    /** 机构名称 */
    private String organizationName;

    /** 企业logo */
    private String enterpriseLogo;

    /** 经营品牌 */
    private String brandManagement;

    /** 统一信用码 */
    private String creditCode;

    /** 法人 */
    private String legalPerson;

    /** 法人身份证号 */
    private String legalPersonIdCard;

    /** 电话 */
    private String phone;

    /** 详细地区 */
    private String areaDetail;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 网址 */
    private String url;

    /** 营业期限 */
    private String businessTerm;

    /** 企业类型 */
    private String enterpriseType;

    /** 参保人数 */
    private Long socialSecurityNum;

    /** 人员规模 */
    private Long staffSize;

    /** 经营范围 */
    private String businessScope;

    /** 经营状态 */
    private String businessStatus;

    /** 成立日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerData;

    /** 注册金额 */
    private BigDecimal registerAmount;

    /** 所属集团 */
    private String membershipGroup;

    /** 行业 */
    private String industry;

    /** 当前行业的最顶层行业 */
    private String topIndustry;

    /** 核准日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkData;

    /** 组织机构代码 */
    private String orgCode;

    /** 工商注册号 */
    private String businessRegisterCode;

    /** 纳税人识别号 */
    private String taxpayerCode;

    /** 登记机关 */
    private String registerAuthority;

    /** 信用分评审状态：0未评审，1已评审 */
    private Long creditRatingStatus;

    /** 是否通过审核：0未评审，1通过，2未通过 */
    private String isApproved;

    /** 标签：守信激励对象、失信惩戒对象 */
    private String tag;

    /** 企业邮箱(已废弃) */
    @Excel(name = "企业邮箱(已废弃)")
    private String enterpriseMailbox;

    /** 是否启用：0启用 1禁用 */
    private Long isEnable;

    /** 是否删除：0未删除 1已删除 */
    @Excel(name = "是否删除：0未删除 1已删除")
    private Long isDelete;

    /** 是否认领:0：否，1：是 */

    private Long isClaim;

    /** 信用状态1：良好，2：一般，3：轻微失信，4：严重失信 */
    private String creditStatus;

    /** 省 */
    private String omit;

    /** 市 */
    private String city;

    /** 区 */
    private String area;

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
    public void setVisitorsFlowRate(String visitorsFlowRate) 
    {
        this.visitorsFlowRate = visitorsFlowRate;
    }

    public String getVisitorsFlowRate() 
    {
        return visitorsFlowRate;
    }
    public void setCreditScore(Long creditScore) 
    {
        this.creditScore = creditScore;
    }

    public Long getCreditScore() 
    {
        return creditScore;
    }
    public void setCreditRank(String creditRank) 
    {
        this.creditRank = creditRank;
    }

    public String getCreditRank() 
    {
        return creditRank;
    }
    public void setOrganizationName(String organizationName) 
    {
        this.organizationName = organizationName;
    }

    public String getOrganizationName() 
    {
        return organizationName;
    }
    public void setEnterpriseLogo(String enterpriseLogo) 
    {
        this.enterpriseLogo = enterpriseLogo;
    }

    public String getEnterpriseLogo() 
    {
        return enterpriseLogo;
    }
    public void setBrandManagement(String brandManagement) 
    {
        this.brandManagement = brandManagement;
    }

    public String getBrandManagement() 
    {
        return brandManagement;
    }
    public void setCreditCode(String creditCode) 
    {
        this.creditCode = creditCode;
    }

    public String getCreditCode() 
    {
        return creditCode;
    }
    public void setLegalPerson(String legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() 
    {
        return legalPerson;
    }
    public void setLegalPersonIdCard(String legalPersonIdCard) 
    {
        this.legalPersonIdCard = legalPersonIdCard;
    }

    public String getLegalPersonIdCard() 
    {
        return legalPersonIdCard;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAreaDetail(String areaDetail) 
    {
        this.areaDetail = areaDetail;
    }

    public String getAreaDetail() 
    {
        return areaDetail;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setBusinessTerm(String businessTerm) 
    {
        this.businessTerm = businessTerm;
    }

    public String getBusinessTerm() 
    {
        return businessTerm;
    }
    public void setEnterpriseType(String enterpriseType) 
    {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseType() 
    {
        return enterpriseType;
    }
    public void setSocialSecurityNum(Long socialSecurityNum) 
    {
        this.socialSecurityNum = socialSecurityNum;
    }

    public Long getSocialSecurityNum() 
    {
        return socialSecurityNum;
    }
    public void setStaffSize(Long staffSize) 
    {
        this.staffSize = staffSize;
    }

    public Long getStaffSize() 
    {
        return staffSize;
    }
    public void setBusinessScope(String businessScope) 
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() 
    {
        return businessScope;
    }
    public void setBusinessStatus(String businessStatus) 
    {
        this.businessStatus = businessStatus;
    }

    public String getBusinessStatus() 
    {
        return businessStatus;
    }
    public void setRegisterData(Date registerData) 
    {
        this.registerData = registerData;
    }

    public Date getRegisterData() 
    {
        return registerData;
    }
    public void setRegisterAmount(BigDecimal registerAmount) 
    {
        this.registerAmount = registerAmount;
    }

    public BigDecimal getRegisterAmount() 
    {
        return registerAmount;
    }
    public void setMembershipGroup(String membershipGroup) 
    {
        this.membershipGroup = membershipGroup;
    }

    public String getMembershipGroup() 
    {
        return membershipGroup;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setTopIndustry(String topIndustry) 
    {
        this.topIndustry = topIndustry;
    }

    public String getTopIndustry() 
    {
        return topIndustry;
    }
    public void setCheckData(Date checkData) 
    {
        this.checkData = checkData;
    }

    public Date getCheckData() 
    {
        return checkData;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setBusinessRegisterCode(String businessRegisterCode) 
    {
        this.businessRegisterCode = businessRegisterCode;
    }

    public String getBusinessRegisterCode() 
    {
        return businessRegisterCode;
    }
    public void setTaxpayerCode(String taxpayerCode) 
    {
        this.taxpayerCode = taxpayerCode;
    }

    public String getTaxpayerCode() 
    {
        return taxpayerCode;
    }
    public void setRegisterAuthority(String registerAuthority) 
    {
        this.registerAuthority = registerAuthority;
    }

    public String getRegisterAuthority() 
    {
        return registerAuthority;
    }
    public void setCreditRatingStatus(Long creditRatingStatus) 
    {
        this.creditRatingStatus = creditRatingStatus;
    }

    public Long getCreditRatingStatus() 
    {
        return creditRatingStatus;
    }
    public void setIsApproved(String isApproved) 
    {
        this.isApproved = isApproved;
    }

    public String getIsApproved() 
    {
        return isApproved;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setEnterpriseMailbox(String enterpriseMailbox) 
    {
        this.enterpriseMailbox = enterpriseMailbox;
    }

    public String getEnterpriseMailbox() 
    {
        return enterpriseMailbox;
    }
    public void setIsEnable(Long isEnable) 
    {
        this.isEnable = isEnable;
    }

    public Long getIsEnable() 
    {
        return isEnable;
    }
    public void setIsDelete(Long isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Long getIsDelete() 
    {
        return isDelete;
    }
    public void setIsClaim(Long isClaim) 
    {
        this.isClaim = isClaim;
    }

    public Long getIsClaim() 
    {
        return isClaim;
    }
    public void setCreditStatus(String creditStatus) 
    {
        this.creditStatus = creditStatus;
    }

    public String getCreditStatus() 
    {
        return creditStatus;
    }
    public void setOmit(String omit) 
    {
        this.omit = omit;
    }

    public String getOmit() 
    {
        return omit;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
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
            .append("visitorsFlowRate", getVisitorsFlowRate())
            .append("creditScore", getCreditScore())
            .append("creditRank", getCreditRank())
            .append("organizationName", getOrganizationName())
            .append("enterpriseLogo", getEnterpriseLogo())
            .append("brandManagement", getBrandManagement())
            .append("creditCode", getCreditCode())
            .append("legalPerson", getLegalPerson())
            .append("legalPersonIdCard", getLegalPersonIdCard())
            .append("phone", getPhone())
            .append("areaDetail", getAreaDetail())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("url", getUrl())
            .append("businessTerm", getBusinessTerm())
            .append("enterpriseType", getEnterpriseType())
            .append("socialSecurityNum", getSocialSecurityNum())
            .append("staffSize", getStaffSize())
            .append("businessScope", getBusinessScope())
            .append("businessStatus", getBusinessStatus())
            .append("registerData", getRegisterData())
            .append("registerAmount", getRegisterAmount())
            .append("membershipGroup", getMembershipGroup())
            .append("industry", getIndustry())
            .append("topIndustry", getTopIndustry())
            .append("checkData", getCheckData())
            .append("orgCode", getOrgCode())
            .append("businessRegisterCode", getBusinessRegisterCode())
            .append("taxpayerCode", getTaxpayerCode())
            .append("registerAuthority", getRegisterAuthority())
            .append("creditRatingStatus", getCreditRatingStatus())
            .append("isApproved", getIsApproved())
            .append("tag", getTag())
            .append("enterpriseMailbox", getEnterpriseMailbox())
            .append("isEnable", getIsEnable())
            .append("isDelete", getIsDelete())
            .append("isClaim", getIsClaim())
            .append("creditStatus", getCreditStatus())
            .append("omit", getOmit())
            .append("city", getCity())
            .append("area", getArea())
            .toString();
    }
}
