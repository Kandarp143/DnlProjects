package kp.beans.data.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * MocEngMst generated by hbm2java
 */
public class MocEngMst implements java.io.Serializable {

    private int engId;
    private int caseId;
    private MocInitMst mocInitMst;
    private String userId;
    private Date actDate;
    private Double l1;
    private String l2;
    private String deptId;

    public MocEngMst() {
        caseId = 0;
        mocInitMst = null;
        userId = "";
        actDate = null;
        l1 = 0.0;
        l2 = "";
        deptId = "";
    }

    public MocEngMst(int caseId) {
        this.caseId = caseId;
    }

    public MocEngMst(int caseId, MocInitMst mocInitMst, String userId, Date actDate, Double l1, String l2, String deptId) {
        this.caseId = caseId;
        this.mocInitMst = mocInitMst;
        this.userId = userId;
        this.actDate = actDate;
        this.l1 = l1;
        this.l2 = l2;
        this.deptId = deptId;
    }

    public MocInitMst getMocInitMst() {
        return this.mocInitMst;
    }

    public void setMocInitMst(MocInitMst mocInitMst) {
        this.mocInitMst = mocInitMst;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getActDate() {
        return this.actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public Double getL1() {
        return this.l1;
    }

    public void setL1(Double l1) {
        this.l1 = l1;
    }

    public String getL2() {
        return this.l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the caseId
     */
    public int getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the engId
     */
    public int getEngId() {
        return engId;
    }

    /**
     * @param engId the engId to set
     */
    public void setEngId(int engId) {
        this.engId = engId;
    }




}
