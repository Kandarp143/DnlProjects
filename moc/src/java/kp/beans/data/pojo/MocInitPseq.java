package kp.beans.data.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1

import kp.logic.GeneralMethods;

/**
 * MocInitPseq generated by hbm2java
 */
public class MocInitPseq implements java.io.Serializable {

    private GeneralMethods gm = new GeneralMethods();

    private int caseId;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String h6;
    private String h7;
    private String h8;
    private String h9;
    private String h10;
    private String h11;
    private String h12;
    private String h13;
    private String h14;
    private MocInitMst mocInitMst;

    public MocInitPseq() {
        h1 = "";
        h2 = "";
        h3 = "";
        h4 = "";
        h5 = "";
        h6 = "";
        h7 = "";
        h8 = "";
        h9 = "";
        h10 = "";
        h11 = "";
        h12 = "";
        h13 = "";
        h14 = "";
        mocInitMst = null;
    }

    public MocInitPseq(int caseId) {
        this.caseId = caseId;
    }

    public MocInitPseq(int caseId, String h1, String h2, String h3, String h4, String h5, String h6, String h7, String h8, String h9, String h10, String h11, String h12, String h13, String h14, MocInitMst mocInitMst) {
        this.caseId = caseId;
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.h6 = h6;
        this.h7 = h7;
        this.h8 = h8;
        this.h9 = h9;
        this.h10 = h10;
        this.h11 = h11;
        this.h12 = h12;
        this.h13 = h13;
        this.h14 = h14;
        this.mocInitMst = mocInitMst;
    }

    public String getH1() {
        return gm.getCheckStatus(this.h1);
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return gm.getCheckStatus(this.h2);
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return gm.getCheckStatus(this.h3);
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return gm.getCheckStatus(this.h4);
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getH5() {
        return gm.getCheckStatus(this.h5);
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getH6() {
        return gm.getCheckStatus(this.h6);
    }

    public void setH6(String h6) {
        this.h6 = h6;
    }

    public String getH7() {
        return gm.getCheckStatus(this.h7);
    }

    public void setH7(String h7) {
        this.h7 = h7;
    }

    public String getH8() {
        return gm.getCheckStatus(this.h8);
    }

    public void setH8(String h8) {
        this.h8 = h8;
    }

    public String getH9() {
        return gm.getCheckStatus(this.h9);
    }

    public void setH9(String h9) {
        this.h9 = h9;
    }

    public String getH10() {
        return gm.getCheckStatus(this.h10);
    }

    public void setH10(String h10) {
        this.h10 = h10;
    }

    public String getH11() {
        return gm.getCheckStatus(this.h11);
    }

    public void setH11(String h11) {
        this.h11 = h11;
    }

    public String getH12() {
        return gm.getCheckStatus(this.h12);
    }

    public void setH12(String h12) {
        this.h12 = h12;
    }

    public String getH13() {
        return gm.getCheckStatus(this.h13);
    }

    public void setH13(String h13) {
        this.h13 = h13;
    }

    public String getH14() {
        return this.h14;
    }

    public void setH14(String h14) {
        this.h14 = h14;
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
     * @return the mocInitMst
     */
    public MocInitMst getMocInitMst() {
        return mocInitMst;
    }

    /**
     * @param mocInitMst the mocInitMst to set
     */
    public void setMocInitMst(MocInitMst mocInitMst) {
        this.mocInitMst = mocInitMst;
    }

}