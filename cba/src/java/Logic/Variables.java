/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.TaxBean;
import Bean.WorkItemBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class Variables {

    //Drop downs
    public static Map<String, String> item = new HashMap<String, String>();
    public static ArrayList<TaxBean> tax = new ArrayList<TaxBean>();
    public static Map<String, String> billitem = new HashMap<String, String>();
    public static Map<String, String> payterm = new HashMap<String, String>();
    public static Map<String, String> itmplant = new HashMap<String, String>();
    public static Map<String, String> itmcc = new HashMap<String, String>();
    public static Map<String, String> sup = new HashMap<String, String>();
    public static Map<String, String> cur = new HashMap<String, String>();
    public static Map<String, String> wo_type = new HashMap<String, String>();
    public static Map<String, String> project = new HashMap<String, String>();
    public static Map<String, String> task = new HashMap<String, String>();

    //Date formatter
    public static SimpleDateFormat dtformat = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    //Workflow Variables

    //Excel
    public static ArrayList<WorkItemBean> xlsitm = new ArrayList<WorkItemBean>();
    public static ArrayList<WorkItemBean> xlsitmwo = new ArrayList<WorkItemBean>();

    public static String sql = "";

}
