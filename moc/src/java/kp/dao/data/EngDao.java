/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import kp.dao.user.UserDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocEngMst;
import kp.beans.data.pojo.MocInitMst;
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
public class EngDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        MocEngMst eng = new MocEngMst();
        MocInitMst mst = new MocInitMst();
        mst.setCaseId(1);
        eng.setActDate(new GeneralMethods().getSysDate());
        eng.setL1(5444.3);
        eng.setL2("Test");
        eng.setDeptId("RRRG");
        eng.setUserId("02949");
        eng.setMocInitMst(mst);
        eng.setCaseId(1);

        EngDao ed = new EngDao();
//        ed.saveEng(eng);
//        ed.updateEng(eng);
//        ed.fecthEng(1);
        ed.fecthEng(1234, "02975");
//        ed.fecthEngList(1);
//        ed.deleteEng(1);
//        ed.saveEngSum(1);
    }

    public int saveEng(MocEngMst bean) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        gm = new GeneralMethods();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                bean.setActDate(gm.getSysDate());
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
        Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "CID : " + bean.getMocInitMst().getCaseId());
        return bean.getMocInitMst().getCaseId();

    }

    public int updateEng(MocEngMst bean) {
        Transaction tx = null;
        Session session = null;
        gm = new GeneralMethods();
        EngDao ed = new EngDao();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocEngMst fetchmst = ed.fecthEng(bean.getMocInitMst().getCaseId(), bean.getUserId());
            fetchmst.setL1(bean.getL1());
            fetchmst.setL2(bean.getL2());
            fetchmst.setDeptId(bean.getDeptId());
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

    public void deleteEng(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocEngMst fetchmst = (MocEngMst) session.get(MocEngMst.class, cid);
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

    public MocEngMst fecthEng(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocEngMst eng = null;
        try {
            tx = session.beginTransaction();
            eng = (MocEngMst) session.get(MocEngMst.class, cid);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "ENG GET :" + eng.getUserId());
        return eng;
    }

    public MocEngMst fecthEng(int cid, String uid) {
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "ENG FETCH CALLED :" + cid + "AND :" + uid);
        MocEngMst mst = new MocEngMst();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getMocEngMstByUser");
            queryObj.setInteger("cid", cid);
            queryObj.setString("uid", uid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                mst.setEngId(((BigDecimal) tuple[0]).intValue());
                mst.setCaseId(((BigDecimal) tuple[1]).intValue());
                mst.setUserId((String) tuple[2]);
                mst.setActDate((Date) tuple[3]);
                mst.setL1(((BigDecimal) tuple[4]).doubleValue());
                mst.setL2((String) tuple[5]);
                mst.setDeptId((String) tuple[6]);
                mst.setMocInitMst((MocInitMst) session.get(MocInitMst.class, cid));
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "ENGMST" + mst.getEngId());

        //check record is null
        if (mst.getEngId() == 0) {
            mst = null;
        }

        return mst;
    }

    public ArrayList<MocEngMst> fecthEngList(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ArrayList<MocEngMst> engs = new ArrayList<>();
        MocEngMst eng;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.createQuery("select a.engId,b.caseId,a.userId,a.actDate,a.l1,a.l2,a.deptId"
                    + " from MocEngMst a inner join a.mocInitMst b where b.caseId = :cid");
            queryObj.setInteger("cid", cid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                eng = new MocEngMst();
                eng.setEngId((int) tuple[0]);
                eng.setCaseId((int) tuple[1]);
                eng.setUserId((String) tuple[2]);
                eng.setActDate((Date) tuple[3]);
                eng.setL1((Double) tuple[4]);
                eng.setL2((String) tuple[5]);
                eng.setDeptId((String) tuple[6]);
                engs.add(eng);
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
        if (engs.isEmpty()) {
            return null;
        } else {
            return engs;
        }
    }

    public int saveEngSum(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        gm = new GeneralMethods();
        ArrayList<MocEngMst> engs = fecthEngList(cid);
        MocEngMst eng = new MocEngMst();
        MocInitMst mst = new MocInitMst();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                double sum = 0;
                for (MocEngMst engBean : engs) {
                    sum = sum + engBean.getL1();
                }
                mst.setCaseId(cid);
                eng.setMocInitMst(mst);
                eng.setActDate(gm.getSysDate(session));
                eng.setUserId("02948");
                eng.setDeptId("ENG");
                eng.setL2("Total Cost Estimat");
                eng.setL1(sum);
                session.save(eng);
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
        Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "CID : " + eng.getMocInitMst().getCaseId());
        return eng.getMocInitMst().getCaseId();

    }
}
