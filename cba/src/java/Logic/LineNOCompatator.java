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
public class LineNOCompatator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        WorkItemBean s1 = (WorkItemBean) o1;
        WorkItemBean s2 = (WorkItemBean) o2;

        if (s1.LINE_NO == s2.LINE_NO) {
            return 0;
        } else if (s1.LINE_NO > s2.LINE_NO) {
            return 1;
        } else {
            return -1;
        }

    }
    private static final Logger LOG = Logger.getLogger(LineNOCompatator.class.getName());

}
