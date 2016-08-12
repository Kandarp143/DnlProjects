/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.wf.pojo.MocWfTran;
import kp.dao.user.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class GeneralMethods {

    public static void main(String[] args) throws ParseException {
        GeneralMethods gm = new GeneralMethods();
        gm.parseDate("d");
    }

    // method for parse date to any format
    public String formatDate(Date date, String format) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);

    }

    //string date to java date
    public Date parseDate(String dateString) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String dateAsString = "16/12/2015";
        Date date = sourceFormat.parse(dateString);
        return date;
    }

    //method for get sys date from database
    public Date getSysDate(Session session) {
        SQLQuery query = session.createSQLQuery("select sysdate  from dual");
        Logger.getLogger(GeneralMethods.class.getName()).log(Level.SEVERE, "Date From Daatabase " + (Date) query.uniqueResult());
        return (Date) query.uniqueResult();
    }

    //gt sysdate from database
    public Date getSysDate() {
        Transaction tx = null;
        Session session = null;
        Date date = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select sysdate  from dual");
            Logger.getLogger(GeneralMethods.class.getName()).log(Level.SEVERE, "Date From Daatabase " + (Date) query.uniqueResult());
            date = (Date) query.uniqueResult();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            session.close();
        } finally {
            session.close();
        }
        return date;
    }

    //method for return bean value checkbox
    public String getCheckStatus(String status) {
        String ans = "";
        if ("on".equals(status) || "true".equals(status)) {
            ans = "true";
        } else {
            ans = "false";
        }
        return ans;
    }

    //method for get checked or unchecked checkbox
    public String isASelected(String s) {
        return (s.equals("true")) ? "checked" : "";
    }

    //to view checkbox symbol in view moc fields
    public String getViewCheckBox(String status) {
        String ans = "";
        if (status.equalsIgnoreCase("true")) {
            ans = "<b>&#9745;</b>";
        } else {
            ans = "&#9744;";
        }
        return ans;
    }

    //to check is Query is record or not
    public Boolean existsRec(Query query) {
        return (!query.list().isEmpty());
    }



}
