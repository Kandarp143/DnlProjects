/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.ItemBean;
import java.util.Comparator;

/**
 *
 * @author 02948
 */
public class ItemBeanComp_LOC implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        ItemBean s1 = (ItemBean) o1;
        ItemBean s2 = (ItemBean) o2;
        return s1.LOC_ID.compareTo(s2.LOC_ID);
    }

}
