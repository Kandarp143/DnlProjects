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
public class InvItemBean {

    /**
     * @return the LOG
     */
    public static Logger getLOG() {
        return LOG;
    }

    /**
     * @param aLOG the LOG to set
     */
    public static void setLOG(Logger aLOG) {
        LOG = aLOG;
    }

    private int INVOICE_ID;
    private String ACC_DATE;
    private int INVOICE_LINE_ID;
    private int LINE_NUMBER;
    private String LINE_TYPE_LOOKUP_CODE;
    private float AMOUNT;
    private String DESCRIPTION;
    private int INVENTORY_ITEM_ID;
    private String ITEM_DESCRIPTION;
    private float QUANTITY_INVOICED;
    private float UNIT_PRICE;
    private int ORG_ID;
    private int TAX_CODE_ID;
    private int GLcode_ID;
    private int seg1;
    private int seg2;
    private int seg3;
    private int seg4;
    private int seg5;
    private int seg6;
    private String PROJ;
    private String TASK;
    private String GL_CODE;

    /**
     * @return the INVOICE_LINE_ID
     */
    public int getINVOICE_LINE_ID() {
        return INVOICE_LINE_ID;
    }

    /**
     * @param INVOICE_LINE_ID the INVOICE_LINE_ID to set
     */
    public void setINVOICE_LINE_ID(int INVOICE_LINE_ID) {
        this.INVOICE_LINE_ID = INVOICE_LINE_ID;
    }

    /**
     * @return the LINE_NUMBER
     */
    public int getLINE_NUMBER() {
        return LINE_NUMBER;
    }

    /**
     * @param LINE_NUMBER the LINE_NUMBER to set
     */
    public void setLINE_NUMBER(int LINE_NUMBER) {
        this.LINE_NUMBER = LINE_NUMBER;
    }

    /**
     * @return the LINE_TYPE_LOOKUP_CODE
     */
    public String getLINE_TYPE_LOOKUP_CODE() {
        return LINE_TYPE_LOOKUP_CODE;
    }

    /**
     * @param LINE_TYPE_LOOKUP_CODE the LINE_TYPE_LOOKUP_CODE to set
     */
    public void setLINE_TYPE_LOOKUP_CODE(String LINE_TYPE_LOOKUP_CODE) {
        this.LINE_TYPE_LOOKUP_CODE = LINE_TYPE_LOOKUP_CODE;
    }

    /**
     * @return the AMOUNT
     */
    public float getAMOUNT() {
        return AMOUNT;
    }

    /**
     * @param AMOUNT the AMOUNT to set
     */
    public void setAMOUNT(float AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     * @return the INVENTORY_ITEM_ID
     */
    public int getINVENTORY_ITEM_ID() {
        return INVENTORY_ITEM_ID;
    }

    /**
     * @param INVENTORY_ITEM_ID the INVENTORY_ITEM_ID to set
     */
    public void setINVENTORY_ITEM_ID(int INVENTORY_ITEM_ID) {
        this.INVENTORY_ITEM_ID = INVENTORY_ITEM_ID;
    }

    /**
     * @return the ITEM_DESCRIPTION
     */
    public String getITEM_DESCRIPTION() {
        return ITEM_DESCRIPTION;
    }

    /**
     * @param ITEM_DESCRIPTION the ITEM_DESCRIPTION to set
     */
    public void setITEM_DESCRIPTION(String ITEM_DESCRIPTION) {
        this.ITEM_DESCRIPTION = ITEM_DESCRIPTION;
    }

    /**
     * @return the QUANTITY_INVOICED
     */
    public float getQUANTITY_INVOICED() {
        return QUANTITY_INVOICED;
    }

    /**
     * @param QUANTITY_INVOICED the QUANTITY_INVOICED to set
     */
    public void setQUANTITY_INVOICED(float QUANTITY_INVOICED) {
        this.QUANTITY_INVOICED = QUANTITY_INVOICED;
    }

    /**
     * @return the UNIT_PRICE
     */
    public float getUNIT_PRICE() {
        return UNIT_PRICE;
    }

    /**
     * @param UNIT_PRICE the UNIT_PRICE to set
     */
    public void setUNIT_PRICE(float UNIT_PRICE) {
        this.UNIT_PRICE = UNIT_PRICE;
    }

    /**
     * @return the ORG_ID
     */
    public int getORG_ID() {
        return ORG_ID;
    }

    /**
     * @param ORG_ID the ORG_ID to set
     */
    public void setORG_ID(int ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    /**
     * @return the TAX_CODE_ID
     */
    public int getTAX_CODE_ID() {
        return TAX_CODE_ID;
    }

    /**
     * @param TAX_CODE_ID the TAX_CODE_ID to set
     */
    public void setTAX_CODE_ID(int TAX_CODE_ID) {
        this.TAX_CODE_ID = TAX_CODE_ID;
    }

    /**
     * @return the INVOICE_ID
     */
    public int getINVOICE_ID() {
        return INVOICE_ID;
    }

    /**
     * @param INVOICE_ID the INVOICE_ID to set
     */
    public void setINVOICE_ID(int INVOICE_ID) {
        this.INVOICE_ID = INVOICE_ID;
    }

    /**
     * @return the GLcode_ID
     */
    public int getGLcode_ID() {
        return GLcode_ID;
    }

    /**
     * @param GLcode_ID the GLcode_ID to set
     */
    public void setGLcode_ID(int GLcode_ID) {
        this.GLcode_ID = GLcode_ID;
    }

    /**
     * @return the ACC_DATE
     */
    public String getACC_DATE() {
        return ACC_DATE;
    }

    /**
     * @param ACC_DATE the ACC_DATE to set
     */
    public void setACC_DATE(String ACC_DATE) {
        this.ACC_DATE = ACC_DATE;
    }

    /**
     * @return the seg1
     */
    public int getSeg1() {
        return seg1;
    }

    /**
     * @param seg1 the seg1 to set
     */
    public void setSeg1(int seg1) {
        this.seg1 = seg1;
    }

    /**
     * @return the seg2
     */
    public int getSeg2() {
        return seg2;
    }

    /**
     * @param seg2 the seg2 to set
     */
    public void setSeg2(int seg2) {
        this.seg2 = seg2;
    }

    /**
     * @return the seg3
     */
    public int getSeg3() {
        return seg3;
    }

    /**
     * @param seg3 the seg3 to set
     */
    public void setSeg3(int seg3) {
        this.seg3 = seg3;
    }

    /**
     * @return the seg4
     */
    public int getSeg4() {
        return seg4;
    }

    /**
     * @param seg4 the seg4 to set
     */
    public void setSeg4(int seg4) {
        this.seg4 = seg4;
    }

    /**
     * @return the seg5
     */
    public int getSeg5() {
        return seg5;
    }

    /**
     * @param seg5 the seg5 to set
     */
    public void setSeg5(int seg5) {
        this.seg5 = seg5;
    }

    /**
     * @return the seg6
     */
    public int getSeg6() {
        return seg6;
    }

    /**
     * @param seg6 the seg6 to set
     */
    public void setSeg6(int seg6) {
        this.seg6 = seg6;
    }
    private static Logger LOG = Logger.getLogger(InvItemBean.class.getName());

    /**
     * @return the PROJ
     */
    public String getPROJ() {
        return PROJ;
    }

    /**
     * @param PROJ the PROJ to set
     */
    public void setPROJ(String PROJ) {
        this.PROJ = PROJ;
    }

    /**
     * @return the TASK
     */
    public String getTASK() {
        return TASK;
    }

    /**
     * @param TASK the TASK to set
     */
    public void setTASK(String TASK) {
        this.TASK = TASK;
    }

    /**
     * @return the GL_CODE
     */
    public String getGL_CODE() {
        return GL_CODE;
    }

    /**
     * @param GL_CODE the GL_CODE to set
     */
    public void setGL_CODE(String GL_CODE) {
        this.GL_CODE = GL_CODE;
    }

}
