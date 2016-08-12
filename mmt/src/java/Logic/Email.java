/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.ItemBean;
import Bean.MitBean;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author 02948
 */
public class Email {

    static final Logger logger = Logger.getLogger(Email.class.getName());

    public String AlertBody(ArrayList<MitBean> milist) throws SQLException {
        String x = "";

        x = "<font face='verdana' size='3' color ='black'>"
                + "Item Consume By : " + milist.get(0).getUSER_NAME() + "</br></br>"
                + "Item consumption detail mentioned below</br>"
                + "<p>Please use generated MITNO for futher transaction (EX: SO Creation) </p></br>"
                + "</br></br>"
                + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                + "<tr><th>Item</th><th>Location</th><th>Reciept No </th><th>Lot no</th><th>MITNO</th><th>Consume QTY</th><th>"
                + "purpose</th>";

        for (MitBean bean : milist) {
            x += "<tr>"
                    + "<td>" + bean.getITEM_DESC() + "</td>"
                    + "<td>" + bean.getLOC_ID() + "</td>"
                    + "<td>" + bean.getRECIEPT_NO() + "</td>"
                    + "<td>" + bean.getLOT_NO() + "</td>"
                    + "<td>" + bean.getMITNO() + "</td>"
                    + "<td style='float:right'>" + bean.getCUN_QTY() + "</td>"
                    + "<td>" + bean.getPUR_NAME() + "</td>";
        }
        x += "</table>"
                + "</font>";
        return x;
    }

    public void Send_Alert(ArrayList<MitBean> milist, String nuid) throws EmailException, SQLException {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("200.200.1.1");
        email.setSmtpPort(25);
        email.setFrom("DNL.MMT_Admin@deepaknitrite.com", "No_Reply");
        email.setSubject("Item consumption from Location :" + milist.get(0).getLOC_ID() + " on Date :" + dt1.format(new Date()));
        String x = AlertBody(milist);
        email.setMsg(x);
        GetMethod g = new GetMethod();
        String to = g.Get_Perameter(nuid, "EMAIL_ID", "MMT_USER_MST");
        email.addTo(to);
        email.send();
        logger.log(Level.INFO, "Action Mail Sent To :{0}", to);
    }

}
