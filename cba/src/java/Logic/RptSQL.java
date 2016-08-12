/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class RptSQL {

    //Stocked Item Summary
    public static String SQL1 = "SELECT   ven.vendor_name, SUM (mst.total_val) expanse\n"
            + "    FROM cba_bill_mst mst, cba_wo_mst wo, po_vendors ven\n"
            + "   WHERE mst.po_no = wo.po_no\n"
            + "     AND mst.status <> 'Rejected'\n"
            + "     AND ven.vendor_number = wo.sup_id\n"
            + "GROUP BY ven.vendor_name ORDER BY ven.VENDOR_NAME";
    public static String SQL1_P = "SELECT   ven.vendor_name, SUM (mst.total_val) expanse\n"
            + "    FROM cba_bill_mst mst, cba_wo_mst wo, po_vendors ven\n"
            + "   WHERE mst.po_no = wo.po_no\n"
            + "     AND mst.status <> 'Rejected'\n"
            + "     AND ven.vendor_number = wo.sup_id\n"
            + "     AND mst.cr_date BETWEEN to_date(?,'MM/DD/YYYY HH:MI:SS AM') AND to_date(?,'MM/DD/YYYY HH:MI:SS AM')\n"
            + "GROUP BY ven.vendor_name";

    public static String SQL2 = "SELECT DISTINCT c.bill_no, TO_CHAR (c.bill_date, 'DD-MON-YYYY') bill_date,\n"
            + "                a.item_id item_code, b.description, a.plant production_center,\n"
            + "                d.vendor_name contractor_name, a.rate, SUM (a.qty) quantity,\n"
            + "                ROUND (SUM (a.val), 2) total_amount\n"
            + "           FROM cba_bill_item a,\n"
            + "                mtl_system_items b,\n"
            + "                cba_bill_mst c,\n"
            + "                po_vendors d,\n"
            + "                cba_wo_mst e\n"
            + "          WHERE a.item_id = b.segment1\n"
            + "            AND b.organization_id = 0\n"
            + "            AND c.bill_no = a.bill_no\n"
            + "            AND c.po_no = e.po_no\n"
            + "            AND e.sup_id = d.vendor_number\n"
            + "            AND c.status <> 'Rejected'\n"
            + "       GROUP BY a.item_id,\n"
            + "                b.description,\n"
            + "                a.rate,\n"
            + "                c.bill_date,\n"
            + "                a.plant,\n"
            + "                d.vendor_name,\n"
            + "                c.bill_no,\n"
            + "                c.bill_no\n"
            + "       ORDER BY a.item_id";

    public static String SQL3 = "SELECT   a.item_id item_code, b.description,\n"
            + "         ROUND (SUM (a.val), 2) total_amount\n"
            + "    FROM cba_bill_item a, mtl_system_items b, cba_bill_mst c, cba_wo_mst e\n"
            + "   WHERE a.item_id = b.segment1\n"
            + "     AND b.organization_id = 0\n"
            + "     AND c.bill_no = a.bill_no\n"
            + "     AND c.po_no = e.po_no\n"
            + "     AND c.status <> 'Rejected'\n"
            + "GROUP BY a.item_id, b.description\n"
            + "ORDER BY a.item_id";

    public static String SQL4 = "SELECT DISTINCT usr.full_name person, ccc.plant department,\n"
            + "                mst.bill_no bill_id, mst.total_val total_cost,\n"
            + "                TO_CHAR (mst.bill_date, 'DD-MON-YYYY') bill_date,\n"
            + "                mst.po_no wo_number, ven.vendor_name, mst.status\n"
            + "           FROM cba_bill_mst mst,\n"
            + "                cba_bill_item dt,\n"
            + "                cba_cc_mst ccc,\n"
            + "                cba_wo_mst womst,\n"
            + "                po_vendors ven,\n"
            + "                cba_tran_bill tran,\n"
            + "                cba_user_mst usr\n"
            + "          WHERE mst.bill_no = dt.bill_no\n"
            + "            AND ccc.cc = dt.cc\n"
            + "            AND mst.po_no = womst.po_no\n"
            + "            AND womst.sup_id = ven.vendor_number\n"
            + "            AND mst.bill_no = tran.bill_no\n"
            + "            AND tran.user_id = usr.user_id\n"
            + "            AND tran.stg_c = 7\n"
            + "            AND mst.status <> 'Rejected'\n"
            + "       ORDER BY 1, 2, 3";

        public static String SQL5 = "SELECT distinct inv.invoice_id, inv.invoice_num, ven.vendor_name,\n"
                + "       site.vendor_site_code,TRIM(REGEXP_SUBSTR(bat.batch_name,'[^M|T|W|F|S]+',1,1)) cba_bill_id,\n"
                + "       inv.invoice_amount, inv.amount_paid,\n"
                + "       TO_CHAR (inv.invoice_date, 'DD-MON-YYYY') inv_date,\n"
                + "       TO_CHAR (inv.creation_date, 'DD-MON-YYYY') creation_date\n"
                + "  FROM apps.ap_invoices_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM inv,\n"
                + "       apps.ap_batches_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM bat,\n"
                + "       apps.po_vendors@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM ven,\n"
                + "       apps.po_vendor_sites_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM site,\n"
                + "       apps.ap_invoice_distributions_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM line\n"
                + " WHERE inv.batch_id = bat.batch_id\n"
                + "   AND ven.vendor_id = inv.vendor_id\n"
                + "   AND ven.vendor_id = site.vendor_id\n"
                + "   AND inv.vendor_site_id = site.vendor_site_id\n"
                + "   AND bat.batch_name LIKE 'CBA%'\n"
                + "   AND (inv.created_by = 9043 OR inv.created_by = 3096) \n"
                + "   AND inv.INVOICE_ID = line.invoice_id\n"
                + "   ORDER BY 3,4";

    public static String SQL6 = "SELECT   inv.invoice_num, ven.vendor_name, site.vendor_site_code,\n"
            + "         TRIM(REGEXP_SUBSTR(bat.batch_name,'[^M|T|W|F|S]+',1,1)) cba_bill_id,\n"
            + "         SUM (DECODE (gl.segment4, '25553', 0, line.amount)) item_amount,\n"
            + "         SUM (DECODE (gl.segment4, '25553', line.amount, 0)) tax_amount,\n"
            + "         TO_CHAR (inv.gl_date, 'DD-MON-YY') gl_date, inv.invoice_amount,\n"
            + "         inv.amount_paid, inv.payment_status_flag, fnd.lookup_code,\n"
            + "         inv.attribute_category, fnd.description service_tax_description\n"
            + "    FROM apps.ap_invoices_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM inv,\n"
            + "         apps.ap_batches_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM bat,\n"
            + "         apps.po_vendors@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM ven,\n"
            + "         apps.po_vendor_sites_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM site,\n"
            + "         apps.ap_invoice_distributions_all@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM line,\n"
            + "         apps.gl_code_combinations@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM gl,\n"
            + "         apps.fnd_lookup_values_vl@EDNLAPPSR.REGRESS.RDBMS.DEV.US.ORACLE.COM fnd\n"
            + "   WHERE inv.batch_id = bat.batch_id\n"
            + "     AND ven.vendor_id = inv.vendor_id\n"
            + "     AND ven.vendor_id = site.vendor_id\n"
            + "     AND inv.vendor_site_id = site.vendor_site_id\n"
            + "     AND line.dist_code_combination_id = gl.code_combination_id\n"
            + "     AND bat.batch_name LIKE 'CBA%'\n"
            + "     AND line.line_type_lookup_code = 'ITEM'\n"
            + "     AND (inv.created_by = 9043 OR inv.created_by = 3096)\n"
            + "     AND inv.invoice_id = line.invoice_id\n"
            + "     AND fnd.lookup_code(+) = inv.attribute15\n"
            + "     AND inv.payment_status_flag = 'Y'\n"
            + "GROUP BY inv.invoice_num,\n"
            + "         ven.vendor_name,\n"
            + "         site.vendor_site_code,\n"
            + "         bat.batch_name,\n"
            + "         inv.invoice_amount,\n"
            + "         inv.amount_paid,\n"
            + "         inv.payment_status_flag,\n"
            + "         inv.gl_date,\n"
            + "         fnd.lookup_code,\n"
            + "         inv.attribute_category,\n"
            + "         fnd.description";

    public static String SQL7 = "SELECT   bmst.bill_no bill_id, ven.vendor_name, usr.full_name pending_at,bmst.TOTAL_VAL\n"
            + "    FROM cba_bill_mst bmst,\n"
            + "         cba_tran_bill btran,\n"
            + "         cba_user_mst usr,\n"
            + "         cba_wo_mst womst,\n"
            + "         po_vendors ven\n"
            + "   WHERE bmst.bill_no = btran.bill_no\n"
            + "     AND btran.is_comp IS NULL\n"
            + "     AND usr.user_id = btran.nxt_uid\n"
            + "     AND womst.po_no = bmst.po_no\n"
            + "     AND womst.sup_id = ven.vendor_number\n"
            + "     AND bmst.status NOT IN ('INV Created', 'Approved', 'Rejected')\n"
            + "ORDER BY 2, 1, 3";

    public static String SQL8 = "SELECT   ven.vendor_name, usr.full_name pending_at, COUNT (bmst.bill_no),\n"
            + "         SUM (bmst.total_val)\n"
            + "    FROM cba_bill_mst bmst,\n"
            + "         cba_tran_bill btran,\n"
            + "         cba_user_mst usr,\n"
            + "         cba_wo_mst womst,\n"
            + "         po_vendors ven\n"
            + "   WHERE bmst.bill_no = btran.bill_no\n"
            + "     AND btran.is_comp IS NULL\n"
            + "     AND usr.user_id = btran.nxt_uid\n"
            + "     AND womst.po_no = bmst.po_no\n"
            + "     AND womst.sup_id = ven.vendor_number\n"
            + "     AND bmst.status NOT IN ('INV Created', 'Approved', 'Rejected')\n"
            + "GROUP BY ven.vendor_name, usr.full_name\n"
            + "ORDER BY 1, 2";

    public static String SQL9 = "SELECT   mstb.bill_no, mstb.bill_usr_no, mstw.po_no,\n"
            + "         TO_CHAR (mstb.bill_date, 'DD-MON-YYYY') bill_date,\n"
            + "         TO_CHAR (mstb.cr_date, 'DD-MON-YYYY') cr_date, mstu2.full_name,\n"
            + "         mstw.cur, ven.vendor_name, mstw.site, vensit.vendor_site_code,\n"
            + "         mstb.ou, mstb.val, mstb.tax_val, mstb.total_val, mstb.bill_desc,\n"
            + "         mstb.status, TO_CHAR (trb.act_date, 'DD-MON-YYYY') last_up_date,\n"
            + "         DECODE (mstb.status,\n"
            + "                 'INV Created', '-',\n"
            + "                 'Rejected', '-',\n"
            + "                 mstu.full_name\n"
            + "                ) pending_at,\n"
            + "         msts.stg_name\n"
            + "    FROM cba_tran_bill trb,\n"
            + "         cba_wo_mst mstw,\n"
            + "         cba_bill_mst mstb,\n"
            + "         cba_stg_mst msts,\n"
            + "         cba_user_mst mstu,\n"
            + "         cba_user_mst mstu2,\n"
            + "         po_vendors ven,\n"
            + "         po_vendor_sites_all vensit\n"
            + "   WHERE tran_id IN (SELECT   MAX (tran_id)\n"
            + "                         FROM cba_tran_bill\n"
            + "                     GROUP BY bill_no)\n"
            + "     AND mstb.po_no = mstw.po_no\n"
            + "     AND mstw.sup_id = ven.vendor_number\n"
            + "     AND trb.bill_no = mstb.bill_no\n"
            + "     AND trb.nxt_uid = mstu.user_id\n"
            + "     AND trb.stg_n = msts.stg_id\n"
            + "     AND mstu2.user_id = mstb.user_id\n"
            + "     AND mstw.site = vensit.vendor_site_id\n"
            + "ORDER BY TO_NUMBER (TRIM (trb.bill_no))";
    private static final Logger LOG = Logger.getLogger(RptSQL.class.getName());
}
