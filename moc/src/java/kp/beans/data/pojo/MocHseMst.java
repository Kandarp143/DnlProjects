package kp.beans.data.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * MocHseMst generated by hbm2java
 */
public class MocHseMst implements java.io.Serializable {

    private int caseId;
    private MocInitMst mocInitMst;
    private String userId;
    private Date actDate;
    private String j1;
    private String j2;

    public MocHseMst() {
        caseId = 0;
        mocInitMst = null;
        userId = "";
        actDate = null;
        j1 = "";
        j2 = "";
    }

    public MocHseMst(int caseId) {
        this.caseId = caseId;
    }

    public MocHseMst(int caseId, MocInitMst mocInitMst, String userId, Date actDate, String j1, String j2) {
        this.caseId = caseId;
        this.mocInitMst = mocInitMst;
        this.userId = userId;
        this.actDate = actDate;
        this.j1 = j1;
        this.j2 = j2;
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

    public String getJ1() {
        return this.j1;
    }

    public void setJ1(String j1) {
        this.j1 = j1;
    }

    public String getJ2() {
        return this.j2;
    }

    public void setJ2(String j2) {
        this.j2 = j2;
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

}
