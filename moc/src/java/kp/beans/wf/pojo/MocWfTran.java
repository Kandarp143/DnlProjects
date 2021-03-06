package kp.beans.wf.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1

import java.util.Date;
import kp.beans.data.pojo.MocInitMst;

/**
 * MocWfTran generated by hbm2java
 */
public class MocWfTran implements java.io.Serializable {

    private int tranId;
    private int caseId;
    private String caseName;
    private String caseOwnerName;
    private String caseOwnerId;
    private String crDateString;
    private String actDateString;
    private String unitId;
    private String unitName;
    private String plantId;
    private String plantName;
    private String userC;
    private String userCname;
    private int stgC;
    private String stgCname;
    private int stgN;
    private String stgNname;
    private int act;
    private String actname;
    private Date actDate;
    private String cmt;
    private String userN;
    private String userNname;
    private Character isCompleted;
    private String isCompletedStatus;
    private MocInitMst mocInitMst;
    private String nextPage;
    private String mocStatus;
    private String mocNo;

    public MocWfTran() {
    }

    public MocWfTran(int tranId) {
        this.tranId = tranId;
    }

    public MocWfTran(int tranId, MocInitMst mocInitMst, String userC, int stgC, int stgN, int act, Date actDate, String cmt, String userN, Character isCompleted) {
        this.tranId = tranId;
        this.mocInitMst = mocInitMst;
        this.userC = userC;
        this.stgC = stgC;
        this.stgN = stgN;
        this.act = act;
        this.actDate = actDate;
        this.cmt = cmt;
        this.userN = userN;
        this.isCompleted = isCompleted;
    }

    public int getTranId() {
        return this.tranId;
    }

    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    public String getUserC() {
        return this.userC;
    }

    public void setUserC(String userC) {
        this.userC = userC;
    }

    public int getStgC() {
        return this.stgC;
    }

    public void setStgC(int stgC) {
        this.stgC = stgC;
    }

    public int getStgN() {
        return this.stgN;
    }

    public void setStgN(int stgN) {
        this.stgN = stgN;
    }

    public int getAct() {
        return this.act;
    }

    public void setAct(int act) {
        this.act = act;
    }

    public Date getActDate() {
        return this.actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getCmt() {
        return this.cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getUserN() {
        return this.userN;
    }

    public void setUserN(String userN) {
        this.userN = userN;
    }

    public Character getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(Character isCompleted) {
        this.isCompleted = isCompleted;
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

    /**
     * @return the userCname
     */
    public String getUserCname() {
        return userCname;
    }

    /**
     * @param userCname the userCname to set
     */
    public void setUserCname(String userCname) {
        this.userCname = userCname;
    }

    /**
     * @return the stgCname
     */
    public String getStgCname() {
        return stgCname;
    }

    /**
     * @param stgCname the stgCname to set
     */
    public void setStgCname(String stgCname) {
        this.stgCname = stgCname;
    }

    /**
     * @return the stgNname
     */
    public String getStgNname() {
        return stgNname;
    }

    /**
     * @param stgNname the stgNname to set
     */
    public void setStgNname(String stgNname) {
        this.stgNname = stgNname;
    }

    /**
     * @return the actname
     */
    public String getActname() {
        return actname;
    }

    /**
     * @param actname the actname to set
     */
    public void setActname(String actname) {
        this.actname = actname;
    }

    /**
     * @return the userNname
     */
    public String getUserNname() {
        return userNname;
    }

    /**
     * @param userNname the userNname to set
     */
    public void setUserNname(String userNname) {
        this.userNname = userNname;
    }

    /**
     * @return the isCompletedStatus
     */
    public String getIsCompletedStatus() {
        return isCompletedStatus;
    }

    /**
     * @param isCompletedStatus the isCompletedStatus to set
     */
    public void setIsCompletedStatus(String isCompletedStatus) {
        this.isCompletedStatus = isCompletedStatus;
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
     * @return the caseName
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * @param caseName the caseName to set
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * @return the caseOwnerName
     */
    public String getCaseOwnerName() {
        return caseOwnerName;
    }

    /**
     * @param caseOwnerName the caseOwnerName to set
     */
    public void setCaseOwnerName(String caseOwnerName) {
        this.caseOwnerName = caseOwnerName;
    }

    /**
     * @return the caseOwnerId
     */
    public String getCaseOwnerId() {
        return caseOwnerId;
    }

    /**
     * @param caseOwnerId the caseOwnerId to set
     */
    public void setCaseOwnerId(String caseOwnerId) {
        this.caseOwnerId = caseOwnerId;
    }

    /**
     * @return the unitId
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId the unitId to set
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the plantId
     */
    public String getPlantId() {
        return plantId;
    }

    /**
     * @param plantId the plantId to set
     */
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    /**
     * @return the plantName
     */
    public String getPlantName() {
        return plantName;
    }

    /**
     * @param plantName the plantName to set
     */
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    /**
     * @return the crDateString
     */
    public String getCrDateString() {
        return crDateString;
    }

    /**
     * @param crDateString the crDateString to set
     */
    public void setCrDateString(String crDateString) {
        this.crDateString = crDateString;
    }

    /**
     * @return the actDateString
     */
    public String getActDateString() {
        return actDateString;
    }

    /**
     * @param actDateString the actDateString to set
     */
    public void setActDateString(String actDateString) {
        this.actDateString = actDateString;
    }

    /**
     * @return the nextPage
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage the nextPage to set
     */
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return the mocStatus
     */
    public String getMocStatus() {
        return mocStatus;
    }

    /**
     * @param mocStatus the mocStatus to set
     */
    public void setMocStatus(String mocStatus) {
        this.mocStatus = mocStatus;
    }

    /**
     * @return the mocNo
     */
    public String getMocNo() {
        return mocNo;
    }

    /**
     * @param mocNo the mocNo to set
     */
    public void setMocNo(String mocNo) {
        this.mocNo = mocNo;
    }

}
