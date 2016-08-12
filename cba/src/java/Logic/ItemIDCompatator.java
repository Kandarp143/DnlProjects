
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.WorkItemBean;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class ItemIDCompatator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        WorkItemBean s1 = (WorkItemBean) o1;
        WorkItemBean s2 = (WorkItemBean) o2;
        return s1.getITEM_ID().compareTo(s2.getITEM_ID());
    }
    private static final Logger LOG = Logger.getLogger(ItemIDCompatator.class.getName());

}
