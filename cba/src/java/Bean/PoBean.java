/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class PoBean {

    private String PO_NO;
    private String BILL_NO;
    private String BILL_ID;
    private String INV_NUM;
    private int INV_ID;
    private String INV_REQ_ID;
    public int LINE_NO;

    /**
     * @return the PO_NO
     */
    public String getPO_NO() {
        return PO_NO;
    }

    /**
     * @param PO_NO the PO_NO to set
     */
    public void setPO_NO(String PO_NO) {
        this.PO_NO = PO_NO;
    }

    /**
     * @return the BILL_NO
     */
    public String getBILL_NO() {
        return BILL_NO;
    }

    /**
     * @param BILL_NO the BILL_NO to set
     */
    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    /**
     * @return the BILL_ID
     */
    public String getBILL_ID() {
        return BILL_ID;
    }

    /**
     * @param BILL_ID the BILL_ID to set
     */
    public void setBILL_ID(String BILL_ID) {
        this.BILL_ID = BILL_ID;
    }

    /**
     * @return the INV_NUM
     */
    public String getINV_NUM() {
        return INV_NUM;
    }

    /**
     * @param INV_NUM the INV_NUM to set
     */
    public void setINV_NUM(String INV_NUM) {
        this.INV_NUM = INV_NUM;
    }

    /**
     * @return the INV_ID
     */
    public int getINV_ID() {
        return INV_ID;
    }

    /**
     * @param INV_ID the INV_ID to set
     */
    public void setINV_ID(int INV_ID) {
        this.INV_ID = INV_ID;
    }

    /**
     * @return the INV_REQ_ID
     */
    public String getINV_REQ_ID() {
        return INV_REQ_ID;
    }

    /**
     * @param INV_REQ_ID the INV_REQ_ID to set
     */
    public void setINV_REQ_ID(String INV_REQ_ID) {
        this.INV_REQ_ID = INV_REQ_ID;
    }

    /**
     * @return the LINE_NO
     */
    public int getLINE_NO() {
        return LINE_NO;
    }

    /**
     * @param LINE_NO the LINE_NO to set
     */
    public void setLINE_NO(int LINE_NO) {
        this.LINE_NO = LINE_NO;
    }
    private static final Logger LOG = Logger.getLogger(PoBean.class.getName());

}
