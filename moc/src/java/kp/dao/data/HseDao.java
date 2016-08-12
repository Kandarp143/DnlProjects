/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import kp.dao.user.UserDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocInitMst;
import kp.beans.data.pojo.MocHseMst;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class HseDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        MocHseMst she = new MocHseMst();
        MocInitMst mst = new MocInitMst();
        mst.setCaseId(1);
        she.setActDate(new GeneralMethods().getSysDate());
        she.setJ1("TestSsdfsdH1");
        she.setJ2("TestSH2dfsdf");
        she.setUserId("02932");
        she.setMocInitMst(mst);
        she.setCaseId(1);
        HseDao sd = new HseDao();
//        sd.saveShe(she);
//        sd.updateShe(she);
//        sd.fecthShe(1);
//        sd.deleteShe(1);

    }

    public int saveHse(MocHseMst bean) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        gm = new GeneralMethods();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                bean.setActDate(gm.getSysDate(session));
                session.save(bean);
                transaction.commit();
            } catch (Exception ex) {
                // Log the exception here
                transaction.rollback();
                Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "Exception : ");
                throw ex;
            }
        } finally {
            session.close();
        }
        Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "CID : " + bean.getCaseId());
        return bean.getCaseId();

    }

    public int updateHse(MocHseMst bean) {
        Transaction tx = null;
        Session session = null;
        gm = new GeneralMethods();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocHseMst fetchmst = (MocHseMst) session.get(MocHseMst.class, bean.getMocInitMst().getCaseId());
            fetchmst.setJ1(bean.getJ1());
            fetchmst.setJ2(bean.getJ2());
            fetchmst.setActDate(gm.getSysDate());
            fetchmst.setUserId(bean.getUserId());
            session.clear();
            session.update(fetchmst);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            session.close();
        } finally {
            session.close();
        }
        return bean.getCaseId();
    }

    public void deleteHse(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocHseMst fetchmst = (MocHseMst) session.get(MocHseMst.class, cid);
            session.delete(fetchmst);
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

    public MocHseMst fecthHse(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocHseMst she = null;
        try {
            tx = session.beginTransaction();
            she = (MocHseMst) session.get(MocHseMst.class, cid);
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
        return she;
    }
}
