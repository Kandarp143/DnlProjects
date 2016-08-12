/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author 02948
 */
public class MitBean extends ItemBean {

    private int TRAN_ID;
    private String MITNO;
    private int PUR_ID;
    private String PUR_NAME;
    private String CUS_PO;
    private String USER_ID;
    private String USER_NAME;
    private float CUN_QTY;
    private ItemBean ITEMBEAN;
    private String TRAN_DATE;
    private String CRO_NO;

   
    /**
     * @return the PUR_ID
     */
    public int getPUR_ID() {
        return PUR_ID;
    }

    /**
     * @param PUR_ID the PUR_ID to set
     */
    public void setPUR_ID(int PUR_ID) {
        this.PUR_ID = PUR_ID;
    }

    /**
     * @return the CUS_PO
     */
    public String getCUS_PO() {
        return CUS_PO;
    }

    /**
     * @param CUS_PO the CUS_PO to set
     */
    public void setCUS_PO(String CUS_PO) {
        this.CUS_PO = CUS_PO;
    }

    /**
     * @return the ITEMBEAN
     */
    public ItemBean getITEMBEAN() {
        return ITEMBEAN;
    }

    /**
     * @param ITEMBEAN the ITEMBEAN to set
     */
    public void setITEMBEAN(ItemBean ITEMBEAN) {
        this.ITEMBEAN = ITEMBEAN;
    }

    /**
     * @return the USER_ID
     */
    public String getUSER_ID() {
        return USER_ID;
    }

    /**
     * @param USER_ID the USER_ID to set
     */
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    /**
     * @return the CUN_QTY
     */
    public float getCUN_QTY() {
        return CUN_QTY;
    }

    /**
     * @param CUN_QTY the CUN_QTY to set
     */
    public void setCUN_QTY(float CUN_QTY) {
        this.CUN_QTY = CUN_QTY;
    }

    /**
     * @return the TRAN_ID
     */
    public int getTRAN_ID() {
        return TRAN_ID;
    }

    /**
     * @param TRAN_ID the TRAN_ID to set
     */
    public void setTRAN_ID(int TRAN_ID) {
        this.TRAN_ID = TRAN_ID;
    }

   

    /**
     * @return the USER_NAME
     */
    public String getUSER_NAME() {
        return USER_NAME;
    }

    /**
     * @param USER_NAME the USER_NAME to set
     */
    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    /**
     * @return the TRAN_DATE
     */
    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    /**
     * @param TRAN_DATE the TRAN_DATE to set
     */
    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }

    /**
     * @return the PUR_NAME
     */
    public String getPUR_NAME() {
        return PUR_NAME;
    }

    /**
     * @param PUR_NAME the PUR_NAME to set
     */
    public void setPUR_NAME(String PUR_NAME) {
        this.PUR_NAME = PUR_NAME;
    }

    /**
     * @return the MITNO
     */
    public String getMITNO() {
        return MITNO;
    }

    /**
     * @param MITNO the MITNO to set
     */
    public void setMITNO(String MITNO) {
        this.MITNO = MITNO;
    }

    /**
     * @return the CRO_NO
     */
    public String getCRO_NO() {
        return CRO_NO;
    }

    /**
     * @param CRO_NO the CRO_NO to set
     */
    public void setCRO_NO(String CRO_NO) {
        this.CRO_NO = CRO_NO;
    }

}
