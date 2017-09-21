package com.xiaocui.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/*
 *P2P借贷记录明细（多重负债）
 */
public class LoanInfo implements Serializable {
    private static final long serialVersionUID = -6631542493153878009L;

    private Short borrowType;//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
    private Short borrowState;//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
    private Short borrowAmount;//借款金额
    private Date contractDate;//合同日期
    private Short loanPeriod;//批贷期数
    private Short repayState;//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
    private Long arrearsAmount; //欠款金额
    private String companyCode; //公司代码

    /**
     * 获取公司代码
     *
     * @return
     */
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * 获取借款类型
     *
     * @return
     */
    public Short getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(Short borrowType) {
        if (borrowType == null)
            borrowType = 0;
        this.borrowType = borrowType;
    }

    /**
     * 获取借款状态
     *
     * @return
     */
    public Short getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(Short borrowState) {
        if (borrowState == null)
            borrowState = 0;
        this.borrowState = borrowState;
    }

    /**
     * 获取借款金额
     *
     * @return
     */
    public Short getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Short borrowAmount) {
        if (borrowAmount == null)
            borrowAmount = 0;
        this.borrowAmount = borrowAmount;
    }

    /**
     * 获取合同日期
     *
     * @return
     */
    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        if (contractDate == null) {
            LocalDateTime localDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            contractDate = date;
        }
        this.contractDate = contractDate;
    }

    /**
     * 获取批贷期数
     *
     * @return
     */
    public Short getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Short loanPeriod) {
        if (loanPeriod == null)
            loanPeriod = 0;
        this.loanPeriod = loanPeriod;
    }

    /**
     * 获取还款状态
     *
     * @return
     */
    public Short getRepayState() {
        return repayState;
    }

    public void setRepayState(Short repayState) {
        if (repayState == null)
            repayState = 0;
        this.repayState = repayState;
    }

    /**
     * 获取欠款金额
     *
     * @return
     */
    public Long getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(Long arrearsAmount) {
        if (arrearsAmount == null)
            arrearsAmount = 0L;
        this.arrearsAmount = arrearsAmount;
    }
}

