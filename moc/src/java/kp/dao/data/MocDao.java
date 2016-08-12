/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import java.math.BigDecimal;
import kp.dao.user.UserDao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocInitEle;
import kp.beans.data.pojo.MocInitMst;
import kp.beans.data.pojo.MocInitPip;
import kp.beans.data.pojo.MocInitProc;
import kp.beans.data.pojo.MocInitProd;
import kp.beans.data.pojo.MocInitPseq;
import kp.beans.data.pojo.MocInitReq;
import kp.beans.data.pojo.MocInitRot;
import kp.beans.data.pojo.MocInitSta;
import kp.beans.data.pojo.MocInitTskDt;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class MocDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        //FOR CREATE MOC
        MocInitReq a = new MocInitReq(0, "on", "on", "on", "kandarp", "on", "on",
                "on", "on", null, null, "on", "on", "on", "on", null);
        MocInitSta b = new MocInitSta(0, "on", null, null, "on", null, "on",
                null, null, "on", "on", "on", null, null, "on", null, null,
                null, null);
        MocInitRot c = new MocInitRot(0, "on", null, "on", null, "on", null,
                null, null, null, null, null, null, null);
        MocInitPip d = new MocInitPip(0, "on", "on", "on", "on", "on", "on",
                "on", null);
        MocInitEle e = new MocInitEle(0, null, "on", "on",
                "on", "on", "on", "on", null);
        MocInitProd f = new MocInitProd(0, "on", "on", null, null, null,
                null, null);
        MocInitProc g = new MocInitProc(0, null, "on", null, null, null, null,
                "on", "on", "on", "on", "on", "on", null, null, null, null,
                "on", "on", null, null, null, null, null, null, null, null,
                null, null);
        MocInitPseq h = new MocInitPseq(0, null, "on", null, null, null, null,
                "on", "on", "on", "on", "on", "on", null, null, null);
        MocInitMst mst = new MocInitMst();
        mst.setUserId("02948");
        mst.setUnitId("NDS");
        LinkedHashSet<MocInitTskDt> tasks = new LinkedHashSet<>();
        LinkedHashSet<MocInitTskDt> uptasks = new LinkedHashSet<>();
        for (int i = 1; i <= 17; i++) {
            MocInitTskDt bean = new MocInitTskDt();
            bean.setDocId(i);
            bean.setResParty("02948");
            bean.setUp("on");
            bean.setTarDate(new GeneralMethods().getSysDate());
            bean.setMocInitMst(mst);
            tasks.add(bean);
        }
        mst.setMocInitTskDts(tasks);
        //UPDATE MOC 
        MocInitMst umst = new MocInitMst();
        umst.setUserId("0294833");
        umst.setUnitId("kandarp");
        umst.setCaseId(12);
        umst.setStatus("Updated");
        for (int i = 1; i <= 17; i++) {
            MocInitTskDt bean = new MocInitTskDt();
            bean.setDocId(i);
            bean.setResParty("02949");
            bean.setUp("off");
            bean.setTarDate(new GeneralMethods().getSysDate());
            bean.setMocInitMst(umst);
            uptasks.add(bean);
        }
        umst.setMocInitTskDts(uptasks);
        MocDao md = new MocDao();
//        md.createMoc(mst, a, b, c, d, e, f, g, h);
//        md.updateMoc(umst, a, b, c, d, e, f, g, h);
//        md.deleteMoc(2);
//        md.fetchMoc(2);
        md.updateMocStatus(1, "Approved");
//        md.deleteMocInitTskDt(1);
//        md.updateMocInitTskDt(umst);
//        md.fetchTaskList(7);
    }

    public int createMoc(MocInitMst mst, MocInitReq a, MocInitSta b, MocInitRot c,
            MocInitPip d, MocInitEle e, MocInitProd f, MocInitProc g, MocInitPseq h) {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        gm = new GeneralMethods();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                mst.setCrDate(gm.getSysDate(session));
                mst.setStatus("Created");
                //set all related class to master
                mst.setMocInitReq(a);
                mst.setMocInitSta(b);
                mst.setMocInitRot(c);
                mst.setMocInitPip(d);
                mst.setMocInitEle(e);
                mst.setMocInitProd(f);
                mst.setMocInitProc(g);
                mst.setMocInitPseq(h);
                a.setMocInitMst(mst);
                b.setMocInitMst(mst);
                c.setMocInitMst(mst);
                d.setMocInitMst(mst);
                e.setMocInitMst(mst);
                f.setMocInitMst(mst);
                g.setMocInitMst(mst);
                h.setMocInitMst(mst);
                session.save(mst);
                transaction.commit();
            } catch (Exception ex) {
                // Log the exception here
                transaction.rollback();
                Logger.getLogger(MocDao.class.getName()).log(Level.SEVERE, "Exception : ");
                throw ex;
            }
        } finally {
            session.close();
        }
        Logger.getLogger(MocDao.class.getName()).log(Level.SEVERE, "CID : " + mst.getCaseId());
        return mst.getCaseId();

    }

    public int updateMoc(MocInitMst mst, MocInitReq a, MocInitSta b, MocInitRot c,
            MocInitPip d, MocInitEle e, MocInitProd f, MocInitProc g, MocInitPseq h) {
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "CALLED UPDATE MOC");
        Transaction tx = null;
        Session session = null;
        try {
            MocDao md = new MocDao();
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocInitMst fetchmst = (MocInitMst) session.get(MocInitMst.class, mst.getCaseId());
            fetchmst.setUserId(mst.getUserId());
            fetchmst.setUnitId(mst.getUserId());
            fetchmst.setAppDate(mst.getAppDate());
            fetchmst.setCaseId(mst.getCaseId());
            fetchmst.setMocNo(mst.getMocNo());
            fetchmst.setStatus(mst.getStatus());
            a.setCaseId(mst.getCaseId());
            b.setCaseId(mst.getCaseId());
            c.setCaseId(mst.getCaseId());
            d.setCaseId(mst.getCaseId());
            e.setCaseId(mst.getCaseId());
            f.setCaseId(mst.getCaseId());
            g.setCaseId(mst.getCaseId());
            h.setCaseId(mst.getCaseId());
            fetchmst.setMocInitReq(a);
            fetchmst.setMocInitSta(b);
            fetchmst.setMocInitRot(c);
            fetchmst.setMocInitPip(d);
            fetchmst.setMocInitEle(e);
            fetchmst.setMocInitProd(f);
            fetchmst.setMocInitProc(g);
            fetchmst.setMocInitPseq(h);
            a.setMocInitMst(fetchmst);
            b.setMocInitMst(fetchmst);
            c.setMocInitMst(fetchmst);
            d.setMocInitMst(fetchmst);
            e.setMocInitMst(fetchmst);
            f.setMocInitMst(fetchmst);
            g.setMocInitMst(fetchmst);
            h.setMocInitMst(fetchmst);
            md.deleteMocInitTskDt(mst.getCaseId());
            LinkedHashSet<MocInitTskDt> uptasks = (LinkedHashSet<MocInitTskDt>) mst.getMocInitTskDts();
            uptasks.stream().forEach((bean) -> {
                bean.setMocInitMst(fetchmst);
            });
            fetchmst.setMocInitTskDts(uptasks);
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
        return mst.getCaseId();
    }

    public void updateMocStatus(int cid, String status) {
        Transaction tx = null;
        Session session = null;
        gm = new GeneralMethods();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocInitMst mst = (MocInitMst) session.get(MocInitMst.class, cid);
            mst.setStatus(status);
            String unit = mst.getUnitId();
            mst.setUnitId(unit);
            // If moc in approved state
            if (status.equals("Approved")) {
                Date sysdate = gm.getSysDate(session);
                String year = gm.formatDate(sysdate, "YY");
                String sql = "SELECT COUNT (*)\n"
                        + "  FROM moc_init_mst\n"
                        + " WHERE unit_id = '" + unit + "' AND SUBSTR (moc_no, 4, 2) = '" + year + "'";
                SQLQuery query = session.createSQLQuery(sql);
                int count = ((Number) query.uniqueResult()).intValue();
                String mocno = "MOC" + year + "/" + unit + "-" + String.format("%3s", ++count).replace(' ', '0');
                //Set moc no and app date
                mst.setMocNo(mocno);
                mst.setAppDate(sysdate);
            }
            session.update(mst);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            session.close();
        } catch (ParseException ex) {
            Logger.getLogger(MocDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
    }

    public void deleteMoc(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocInitMst fetchmst = (MocInitMst) session.get(MocInitMst.class, cid);
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

    public MocInitMst fetchMoc(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocInitMst moc = null;
        try {
            tx = session.beginTransaction();
            moc = (MocInitMst) session.get(MocInitMst.class, cid);
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

        return moc;
    }

    public ArrayList<MocInitTskDt> fetchTaskList(int cid) {
        ArrayList<MocInitTskDt> out = new ArrayList<>();
        MocInitTskDt bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("fetchTaskList");
            queryObj.setInteger("cid", cid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocInitTskDt();
                bean.setDocId(((BigDecimal) tuple[0]).intValue());
                bean.setDocName((String) tuple[1]);
                bean.setTarDateString((String) tuple[2]);
                bean.setResPartyName((String) tuple[3]);
                bean.setComDateString((String) tuple[4]);
                bean.setUp((String) tuple[5]);
                bean.setTarDate((Date) tuple[6]);
                bean.setResParty((String) tuple[7]);
                bean.setComDate((Date) tuple[8]);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "USER SELECTION : {0}", out.size());
        return out;
    }

    public void deleteMocInitTskDt(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("deleteTskDt");
            queryObj.setInteger("caseId", cid);
            int result = queryObj.executeUpdate();
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", result);
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

    public void updateMocInitTskDt(MocInitMst mst) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocDao md = new MocDao();
        try {
            tx = session.beginTransaction();
            md.deleteMocInitTskDt(mst.getCaseId());
            LinkedHashSet<MocInitTskDt> uptasks = (LinkedHashSet<MocInitTskDt>) mst.getMocInitTskDts();
            uptasks.stream().forEach((bean) -> {
                bean.setMocInitMst(mst);
            });
            mst.setMocInitTskDts(uptasks);
            session.update(mst);

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

    public void updateMocInitTskDate(int cid, ArrayList<MocInitTskDt> tsklist) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (MocInitTskDt b : tsklist) {
                Query queryObj = session.getNamedQuery("updateMocInitTskDate");
                queryObj.setInteger("cid", cid);
                queryObj.setInteger("doc_id", b.getDocId());
                queryObj.setDate("com_date", b.getComDate());
                int result = queryObj.executeUpdate();
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

    }

}
