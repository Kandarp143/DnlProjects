/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.user;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import kp.beans.user.pojo.MocUserLoginHistory;
import kp.beans.user.pojo.MocUserMst;
import kp.beans.user.pojo.MocUserPass;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class LoginDao {
    //add login history
    //update login history
    //auth user

    private GeneralMethods gm;

    public static void main(String[] args) {
        LoginDao logindao = new LoginDao();
//        logindao.addloginHistory("02948");
//        logindao.updateloginHistory(1);
        logindao.authUser("02949", "02948");

    }

    //add user login history
    public int addloginHistory(String user_id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocUserLoginHistory loginghistory = new MocUserLoginHistory();
        MocUserMst usrmst = new MocUserMst();
        gm = new GeneralMethods();
        try {
            tx = session.beginTransaction();
            usrmst.setUserId(user_id);
            loginghistory.setMocUserMst(usrmst);
            loginghistory.setLoginTime(gm.getSysDate(session));
            int id = (int) session.save(loginghistory);
            tx.commit();
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Session ID " + loginghistory.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }
        return loginghistory.getId();
    }

    //update user logout time
    public void updateloginHistory(int sessionid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        gm = new GeneralMethods();
        try {
            tx = session.beginTransaction();
            MocUserLoginHistory l = (MocUserLoginHistory) session.get(MocUserLoginHistory.class, sessionid);
            l.setId(sessionid);
            l.setLogoutTime(gm.getSysDate(session));
            session.update(l);
            tx.commit();
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Session ID {0}", l.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }

    }

    //authanticate user at login time
    public boolean authUser(String user_id, String password) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Boolean isUserExsist = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("authUser");
            queryObj.setString("user_id", user_id);
            queryObj.setString("password", password);
            List<MocUserPass> records = queryObj.list();
            if (records.size() > 0) {
                isUserExsist = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Login ANS :" + isUserExsist);
        return isUserExsist;
    }
}
