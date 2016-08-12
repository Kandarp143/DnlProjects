/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.WFBean;

/**
 *
 * @author 02948
 */
public interface WFInter {

    public void addUserAct(WFBean bean);

    public void addTranaction(WFBean bean, Boolean isWO);

    public void updateTranFlag(WFBean bean, Boolean isWO);

}
