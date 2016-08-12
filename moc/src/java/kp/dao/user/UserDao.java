/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.mst.pojo.MocUserSelection;
import kp.beans.user.pojo.MocUserHier;
import kp.beans.user.pojo.MocUserMst;
import kp.beans.user.pojo.MocUserPass;
import kp.beans.user.pojo.MocUserRoleDt;
import kp.beans.user.pojo.MocUserRoleMst;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class UserDao {

    private GeneralMethods gm;

    //test main method to call and check output
    public static void main(String[] args) {
        UserDao usrDao = new UserDao();
        MocUserMst usrBean = new MocUserMst();
        MocUserPass usrPass = new MocUserPass();
        HashSet usrRoles = new HashSet();

        //Set User Bean
//        usrBean.setUserId("02949");
//        usrBean.setUserName("Kandarp Patel");
//        usrBean.setEmailId("kppatel@deepaknitrite.com");
//        usrBean.setUnit("CRP");
//        usrBean.setDept("ITS");
//        usrBean.setUserType("EMP");
        //Set User Pass
        usrPass.setPassword("02948");
        //Set Role
//        usrRoles.add(new MocUserRoleDt(0, usrBean, new MocUserRoleMst("SY", "System Admin", "ACC_ROLE", null, null)));
//        usrRoles.add(new MocUserRoleDt(0, usrBean, new MocUserRoleMst("CR", "Creator", "WF_ROLE", null, null)));
//        usrRoles.add(new MocUserRoleDt(0, usrBean, new MocUserRoleMst("ATT", "Add Attachment", "WF_ROLE", null, null)));
//        usrRoles.add(new MocUserRoleDt(0, usrBean, new MocUserRoleMst("DEL", "Delete Records", "WF_ROLE", null, null)));
        //Create User
//        usrDao.createUser(usrBean, usrPass, usrRoles);
        //Delete User
//        usrDao.deleteUser("02949");
        //Update User
//        MocUserMst usrBean2 = new MocUserMst();
//        usrBean2.setUserId("02949");
//        usrBean2.setUserName("Jalpa Patel");
//        usrDao.updateUser(usrBean2);
//        usrDao.getUserAccRole("02948");
//        usrDao.isUserCreator("02948");
        usrDao.getUserHierarchy("01565");
    }

    //create new user
    public void createUser(MocUserMst usr, MocUserPass usrPass, HashSet roles) {
        gm = new GeneralMethods();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Default Set
            usr.setCreationDate(gm.getSysDate(session));
            usr.setIsActive('Y');
            usrPass.setEffDate(gm.getSysDate(session));
            //Reference 
            usr.setMocUserPass(usrPass);
            usrPass.setMocUserMst(usr);
            usr.setMocUserRoleDts(roles);
            //Saving
            session.save(usr);
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

    }

    //update user
    public void updateUser(MocUserMst bean) {
        gm = new GeneralMethods();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocUserMst user = (MocUserMst) session.get(MocUserMst.class, bean.getUserId());
            user.setUserName(bean.getUserName());
            user.setEmailId(bean.getEmailId());
            user.setUnit(bean.getUnit());
            user.setDept(bean.getDept());
            user.setIsActive(bean.getIsActive());
            user.setUserType(bean.getUserType());
            user.setUpdatedDate(gm.getSysDate(session));
            session.update(user);
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

    }

    //fetch user based on user id
    public MocUserMst fetchUser(String user_id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocUserMst user = null;
        try {
            tx = session.beginTransaction();
            user = (MocUserMst) session.get(MocUserMst.class, user_id);
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
        return user;
    }

    //delete user
    public void deleteUser(String userID) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocUserMst user = (MocUserMst) session.get(MocUserMst.class, userID);
            session.delete(user);
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

    }

    //get user acc role
    public String getUserAccRole(String user_id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        String usrRole = "";
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getAccRole");
            queryObj.setString("userid", user_id);
            usrRole = (String) queryObj.uniqueResult();
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "usrRole :" + usrRole);
        return usrRole;
    }

    //check user is creator or not
    public boolean isUserCreator(String user_id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Boolean isUserCreator = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("isCreator");
            queryObj.setString("user_id", user_id);
            List<MocUserPass> records = queryObj.list();
            if (records.size() > 0) {
                isUserCreator = true;
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Is cretor :" + isUserCreator);
        return isUserCreator;
    }

    //fetch list of user by unit
    public ArrayList<MocUserMst> getUserByUnit(String unit) {
        ArrayList<MocUserMst> ans = new ArrayList<>();
        MocUserMst bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getUserByUnit");
            queryObj.setString("unit", unit);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserMst();
                bean.setUserId((String) tuple[0]);
                bean.setUserName((String) tuple[1]);
                bean.setEmailId((String) tuple[2]);
                ans.add(bean);
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
        return ans;
    }

    //get spacific user's workflow hierarchy
    public ArrayList<MocUserHier> getUserHierarchy(String userId) {
        ArrayList<MocUserHier> out = new ArrayList<>();
        MocUserHier bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getUserHierarchy");
            queryObj.setString("uid", userId);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserHier();
                bean.setStgId(((BigDecimal) tuple[0]).intValue());
                bean.setWfUserId((String) tuple[1]);
                out.add(bean);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "USER HIERARCHY : {0}", out.size());
        return out;
    }

}
