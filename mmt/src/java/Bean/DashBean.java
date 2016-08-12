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
public class DashBean extends ItemBean {
    
    
    private String CUS_NO;
    private String CUS_NAME;
    private String GLDATE;

    /**
     * @return the CUS_NO
     */
    public String getCUS_NO() {
        return CUS_NO;
    }

    /**
     * @param CUS_NO the CUS_NO to set
     */
    public void setCUS_NO(String CUS_NO) {
        this.CUS_NO = CUS_NO;
    }

    /**
     * @return the CUS_NAME
     */
    public String getCUS_NAME() {
        return CUS_NAME;
    }

    /**
     * @param CUS_NAME the CUS_NAME to set
     */
    public void setCUS_NAME(String CUS_NAME) {
        this.CUS_NAME = CUS_NAME;
    }

    /**
     * @return the GLDATE
     */
    public String getGLDATE() {
        return GLDATE;
    }

    /**
     * @param GLDATE the GLDATE to set
     */
    public void setGLDATE(String GLDATE) {
        this.GLDATE = GLDATE;
    }

}
