/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.user.pojo.MocUserMst;
import kp.beans.wf.pojo.MocWfAtt;
import kp.dao.user.UserDao;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

/**
 *
 * @author 02948
 */
public class AttDao {
    
    private GeneralMethods gm;
    static final Logger logger = Logger.getLogger(AttDao.class.getName());
    
    public static void main(String[] args) {
        MocWfAtt fb = new MocWfAtt();
        MocUserMst usr = new MocUserMst();
        usr.setUserId("02948");
        fb.setAttType("ATT");
        fb.setCaseId(Integer.parseInt("1"));
        fb.setFName("asd");
        fb.setFPath("attachment" + "\\" + "asd");
        fb.setMocUserMst(usr);
        AttDao attdao = new AttDao();
        attdao.saveAtt(fb);
    }
    
    public int saveAtt(MocWfAtt bean) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        logger.log(Level.SEVERE, "SAVE ATTACHMENT HAS BEEN CALLED ");
        gm = new GeneralMethods();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                bean.setActDate(gm.getSysDate(session));
                if (bean.getAttType().equalsIgnoreCase("RPT")) {
                    //Generate Report
                    session.doWork(new Work() {
                        @Override
                        public void execute(Connection cnctn) throws SQLException {
                            try {
                                String abslute_path = bean.getFPath() + "report";
                                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                                Map parameters = new HashMap();
                                parameters.put("cid", bean.getCaseId());
                                JasperDesign jasperDesign = JRXmlLoader.load(abslute_path + "\\Jrxml\\MocReport.jrxml");
                                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cnctn);
                                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
                                bean.setFName("MOC_" + bean.getCaseId() + "_REPORT.pdf");
                                logger.log(Level.SEVERE, "Report Created :{0}", abslute_path + "\\" + bean.getFName());
                                JasperExportManager.exportReportToPdfFile(jasperPrint, abslute_path + "\\" + bean.getFName());
                                bean.setFPath("report\\" + bean.getFName());
                            } catch (JRException ex) {
                                Logger.getLogger(AttDao.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                } else {
                    Logger.getLogger(AttDao.class.getName()).log(Level.SEVERE, "ATTACHMENT :" + bean.getCaseId());
                }
                Logger.getLogger(AttDao.class.getName()).log(Level.SEVERE, "SAVING IT :" + bean.getCaseId());
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
    
    public ArrayList<MocWfAtt> getMocAtt(int cid) {
        ArrayList<MocWfAtt> ans = new ArrayList<>();
        MocWfAtt bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getMocAtt");
            queryObj.setInteger("cid", cid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocWfAtt();
                bean.setCaseId(cid);
                bean.setUserName((String) tuple[1]);
                bean.setActDateString((String) tuple[2]);
                bean.setFName((String) tuple[3]);
                bean.setFPath((String) tuple[4]);
                bean.setAttType((String) tuple[5]);
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
}
