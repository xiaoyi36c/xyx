package cn.wolfcode.business.manage.credit.orgconst;

/**
 * 机构信用状态 枚举类
 * 信用状态1：良好，2：一般，3：轻微失信，4：严重失信
 */
public enum OrgCreditStatus {
    GOOD(1, "良好"),
    GENERAL(2, "一般"),
    SLIGHTLY_DISHONEST(3, "轻微失信"),
    SERIOUSLY_DISHONEST(4, "严重失信");

    private int code;
    private String name;

    OrgCreditStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OrgCreditStatus getOrgCreditStatus(int code) {
        for (OrgCreditStatus orgCreditStatus : OrgCreditStatus.values()) {
            if (orgCreditStatus.getCode() == code) {
                return orgCreditStatus;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
