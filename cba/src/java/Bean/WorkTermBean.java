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
public class WorkTermBean extends PoBean {

    private String TERM_ID;
    private String TERM_DESC;

    /**
     * @return the TERM_ID
     */
    public String getTERM_ID() {
        return TERM_ID;
    }

    /**
     * @param TERM_ID the TERM_ID to set
     */
    public void setTERM_ID(String TERM_ID) {
        this.TERM_ID = TERM_ID;
    }

    /**
     * @return the TERM_DESC
     */
    public String getTERM_DESC() {
        return TERM_DESC;
    }

    /**
     * @param TERM_DESC the TERM_DESC to set
     */
    public void setTERM_DESC(String TERM_DESC) {
        this.TERM_DESC = TERM_DESC;
    }
    private static final Logger LOG = Logger.getLogger(WorkTermBean.class.getName());

}
