/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author 02948
 */
public class RptSQL {

    //Stocked Item Summary
    public static String SQL1 = "SELECT   ROUND (DECODE (aa.open_stock - dd.open_s,\n"
            + "                        NULL, aa.open_stock,\n"
            + "                        aa.open_stock - dd.open_s\n"
            + "                       ),\n"
            + "                2\n"
            + "               ) opening,\n"
            + "         ROUND (NVL (bb.total_recieved, 0), 2) monthly,\n"
            + "         ROUND (NVL (cc.total_con, 0), 2) consume,\n"
            + "         ROUND (DECODE (  (NVL (aa.open_stock, 0) + NVL (bb.total_recieved, 0)\n"
            + "                          )\n"
            + "                        - (NVL (cc.total_con, 0))\n"
            + "                        - dd.open_s,\n"
            + "                        NULL, (  NVL (aa.open_stock, 0)\n"
            + "                               + NVL (bb.total_recieved, 0)\n"
            + "                            )\n"
            + "                         - (NVL (cc.total_con, 0)),\n"
            + "                          (NVL (aa.open_stock, 0) + NVL (bb.total_recieved, 0)\n"
            + "                          )\n"
            + "                        - (NVL (cc.total_con, 0))\n"
            + "                        - dd.open_s\n"
            + "                       ),\n"
            + "                2\n"
            + "               ) net,\n"
            + "         aa.item_code, aa.item_desc, aa.loc_id, aa.in_transit_loc,\n"
            + "         bb.item_code, bb.item_desc, bb.loc_id, bb.in_transit_loc\n"
            + "    FROM (SELECT   SUM (a.qty) open_stock, a.item_code, a.item_desc, a.loc_id,\n"
            + "                   a.in_transit_loc\n"
            + "              FROM mmt_onhand_mst a\n"
            + "             WHERE TRUNC (grn_date, 'MM') < TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "               AND a.status <> 'closed'\n"
            + "          GROUP BY a.item_code, a.item_desc, a.loc_id, a.in_transit_loc\n"
            + "          ORDER BY item_code) aa\n"
            + "         FULL OUTER JOIN\n"
            + "         (SELECT   SUM (c.qty) total_recieved, c.item_code, c.item_desc,\n"
            + "                   c.loc_id, c.in_transit_loc\n"
            + "              FROM mmt_onhand_mst c\n"
            + "             WHERE TRUNC (grn_date, 'MM') >= TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "               AND c.status <> 'closed'\n"
            + "          GROUP BY c.item_code, c.item_desc, c.loc_id, c.in_transit_loc\n"
            + "          ORDER BY item_code) bb\n"
            + "         ON aa.loc_id = bb.loc_id AND aa.item_code = bb.item_code\n"
            + "         FULL OUTER JOIN\n"
            + "         (SELECT   SUM (a.qty) total_con, a.item_code, a.loc_id\n"
            + "              FROM mmt_tran_mst a\n"
            + "             WHERE TRUNC (a.tran_date, 'MM') >=\n"
            + "                                               TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "          GROUP BY a.item_code, a.loc_id) cc\n"
            + "         ON aa.loc_id = cc.loc_id AND aa.item_code = cc.item_code\n"
            + "         FULL OUTER JOIN\n"
            + "         (SELECT   SUM (a.qty) open_s, a.item_code, a.loc_id\n"
            + "              FROM mmt_tran_mst a\n"
            + "             WHERE TRUNC (a.tran_date, 'MM') < TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "          GROUP BY a.item_code, a.loc_id) dd\n"
            + "         ON aa.loc_id = dd.loc_id AND aa.item_code = dd.item_code\n"
            + " ORDER BY aa.item_code, cc.item_code, aa.loc_id";

    public static String SQL2 = "SELECT   tran.item_code, tran.item_desc, tran.mitno, usr.full_name,\n"
            + "         cun.purpose, TO_CHAR (tran.tran_date, 'DD-MON-YYYY') tran_date,\n"
            + "         tran.cus_po, tran.loc_id, tran.receipt_no, tran.lot_no,\n"
            + "         tran.pre_ship, tran.qty\n"
            + "    FROM mmt_onhand_mst mst,\n"
            + "         mmt_tran_mst tran,\n"
            + "         mmt_user_mst usr,\n"
            + "         mmt_cun_purpose cun\n"
            + "   WHERE mst.receipt_no = tran.receipt_no\n"
            + "     AND mst.item_code = tran.item_code\n"
            + "     AND mst.lot_no = tran.lot_no\n"
            + "     AND mst.pre_ship = tran.pre_ship\n"
            + "     AND tran.user_id = usr.user_id\n"
            + "     AND tran.pur_id = cun.pur_id\n"
            + "ORDER BY tran.mitno";

    public static String SQL3 = "SELECT receipt_no, TO_CHAR (grn_date, 'DD-MON-YYYY') grn_date, po_no, lot_no,\n"
            + "       inv_no, so_no, loc_id, qty, uom, item_code, item_desc, post_ship,\n"
            + "       pre_ship, status, rec_status, re_qty,container_no\n"
            + "  FROM mmt_onhand_mst";

    public static String SQL4 = "SELECT DISTINCT db.customer_name, jsl.excise_invoice_no,\n"
            + "                TO_CHAR (jsl.excise_invoice_date, 'DD-MON-YYYY') inv_date,\n"
            + "                rx.trx_number ar_number,\n"
            + "                TO_CHAR (db.due_date, 'DD-MON-YYYY') due_date,\n"
            + "                itm.segment1 item_code, itm.description item_description,\n"
            + "                db.period_name, db.sales_personname sales_person,\n"
            + "                db.credit_limit, db.currency_code,\n"
            + "                db.totalall_buk total_outstanding\n"
            + "           FROM apps.ja_in_so_picking_lines jsl,\n"
            + "                apps.ra_customer_trx_lines_all rc,\n"
            + "                apps.ra_customer_trx_all rx,\n"
            + "                apps.xxmis_debtor_ason db,\n"
            + "                apps.mtl_system_items itm\n"
            + "          WHERE TO_CHAR (jsl.delivery_id) = rc.interface_line_attribute3\n"
            + "            AND rc.customer_trx_id = rx.customer_trx_id\n"
            + "            AND db.trx_number = rx.trx_number\n"
            + "            AND itm.inventory_item_id = jsl.inventory_item_id\n"
            + "            AND db.company_name = 'DNL DHJ'\n"
            + "            AND jsl.organization_id = '6434'\n"
            + "            -- AND jsl.EXCISE_INVOICE_NO='DUSA/15/3'\n"
            + "            AND db.period_name = TO_CHAR (SYSDATE, 'Mon-YY')";

    public static String SQL4_1 = "SELECT DISTINCT db.customer_name, jsl.excise_invoice_no,\n"
            + "                TO_CHAR (jsl.excise_invoice_date, 'DD-MON-YYYY') inv_date,\n"
            + "                rx.trx_number ar_number,\n"
            + "                TO_CHAR (db.due_date, 'DD-MON-YYYY') due_date,\n"
            + "                itm.segment1 item_code, itm.description item_description,\n"
            + "                db.period_name, db.sales_personname sales_person,\n"
            + "                db.credit_limit, db.currency_code,\n"
            + "                db.totalall_buk total_outstanding\n"
            + "           FROM apps.ja_in_so_picking_lines jsl,\n"
            + "                apps.ra_customer_trx_lines_all rc,\n"
            + "                apps.ra_customer_trx_all rx,\n"
            + "                apps.xxmis_debtor_ason db,\n"
            + "                apps.mtl_system_items itm\n"
            + "          WHERE TO_CHAR (jsl.delivery_id) = rc.interface_line_attribute3\n"
            + "            AND rc.customer_trx_id = rx.customer_trx_id\n"
            + "            AND db.trx_number = rx.trx_number\n"
            + "            AND itm.inventory_item_id = jsl.inventory_item_id\n"
            + "            AND db.company_name = 'DNL DHJ'\n"
            + "            AND jsl.organization_id = '6434'\n"
            + "            -- AND jsl.EXCISE_INVOICE_NO='DUSA/15/3'\n";

    public static String SQL5_1 = "SELECT   a.item_desc, a.loc_id, ROUND (SUM (a.qty), 2)\n"
            + "    FROM mmt_tran_mst a, mmt_cun_purpose b\n"
            + "   WHERE a.pur_id = b.pur_id\n"
            + "GROUP BY a.item_desc, a.loc_id\n"
            + "ORDER BY a.ITEM_DESC,a.LOC_ID";
    public static String SQL5_2 = "SELECT   a.item_desc, b.purpose, ROUND (SUM (a.qty), 2)\n"
            + "    FROM mmt_tran_mst a, mmt_cun_purpose b\n"
            + "   WHERE a.pur_id = b.pur_id\n"
            + "GROUP BY  a.item_desc,b.purpose\n"
            + "ORDER BY a.ITEM_DESC,b.PURPOSE";
    public static String SQL5_3 = "SELECT   a.item_desc, c.full_name, ROUND (SUM (a.qty), 2)\n"
            + "    FROM mmt_tran_mst a, mmt_user_mst c\n"
            + "   WHERE a.user_id = c.user_id\n"
            + "GROUP BY a.item_desc, c.full_name\n"
            + "ORDER BY a.item_desc, c.full_name";

    public static String SQL6 = "SELECT   xarmi.customer_name, SUM (xarmi.amount_applied) total_collection,\n"
            + "         xarmi.receipt_number, xarmi.invoice_currency_code currency_code\n"
            + "    FROM apps.xxmis_sales_detail_ason xsds,\n"
            + "         apps.xxmis_ar_receipt_matching_info xarmi\n"
            + "   WHERE xsds.trx_number = xarmi.trx_number\n"
            + "     AND xsds.org_id = xarmi.organization_id\n"
            + "     AND xsds.org_id = 4454\n"
            + "GROUP BY xarmi.customer_name,\n"
            + "         xarmi.receipt_number,\n"
            + "         xarmi.invoice_currency_code\n"
            + "ORDER BY xarmi.receipt_number,xarmi.customer_name";

    public static String SQL6_1 = "SELECT DISTINCT xarmi.customer_name,xarmi.CUSTOMER_NUMBER\n"
            + "           FROM apps.xxmis_sales_detail_ason xsds,\n"
            + "                apps.xxmis_ar_receipt_matching_info xarmi\n"
            + "          WHERE xsds.trx_number = xarmi.trx_number\n"
            + "            AND xsds.org_id = xarmi.organization_id\n"
            + "            AND xsds.org_id = 4454\n"
            + "       ORDER BY xarmi.customer_name";

    public static String SQL6_2 = "SELECT   xarmi.customer_name, SUM (xarmi.amount_applied) total_collection,\n"
            + "         xarmi.receipt_number, xarmi.invoice_currency_code currency_code\n"
            + "    FROM apps.xxmis_sales_detail_ason xsds,\n"
            + "         apps.xxmis_ar_receipt_matching_info xarmi\n"
            + "   WHERE xsds.trx_number = xarmi.trx_number\n"
            + "     AND xsds.org_id = xarmi.organization_id\n"
            + "     AND xsds.org_id = 4454\n"
            + "GROUP BY xarmi.customer_name,\n"
            + "         xarmi.receipt_number,\n"
            + "         xarmi.invoice_currency_code\n"
            + "ORDER BY xarmi.receipt_number,xarmi.customer_name";

    public static String SQL6_3 = "SELECT   xsds.exc_invoice_no, xarmi.customer_name, xarmi.customer_number,\n"
            + "         xarmi.amount_applied, xarmi.gl_date, xarmi.receipt_number,\n"
            + "         xarmi.trx_number, xarmi.organization_id, xarmi.invoice_currency_code currency_code,\n"
            + "         xarmi.acctd_amount_applied_to\n"
            + "    FROM apps.xxmis_sales_detail_ason xsds,\n"
            + "         apps.xxmis_ar_receipt_matching_info xarmi\n"
            + "   WHERE xsds.trx_number = xarmi.trx_number\n"
            + "     AND xsds.org_id = xarmi.organization_id\n"
            + "     AND xsds.org_id = 4454\n"
            + "     AND xarmi.customer_name = ?"
            + "ORDER BY xarmi.receipt_number,xarmi.customer_name";

    public static String SQL7 = "SELECT   xsds.exc_invoice_no, xarmi.customer_name, xarmi.customer_number,\n"
            + "         xarmi.amount_applied, xarmi.gl_date, xarmi.receipt_number,\n"
            + "         xarmi.trx_number, xarmi.organization_id, xarmi.invoice_currency_code currency_code,\n"
            + "         xarmi.acctd_amount_applied_to\n"
            + "    FROM apps.xxmis_sales_detail_ason xsds,\n"
            + "         apps.xxmis_ar_receipt_matching_info xarmi\n"
            + "   WHERE xsds.trx_number = xarmi.trx_number\n"
            + "     AND xsds.org_id = xarmi.organization_id\n"
            + "     AND xsds.org_id = 4454\n"
            + "ORDER BY xarmi.receipt_number,xarmi.customer_name";

    public static String SQL_VS_MMT = "SELECT   a.item_desc, a.loc_id, a.lot_no, ROUND (SUM (a.re_qty), 3)\n" +
"    FROM mmt_onhand_mst a\n" +
"   WHERE a.status <> 'closed' AND a.loc_id <> 'in-transit'\n" +
"GROUP BY a.item_desc, a.loc_id, a.lot_no\n" +
"ORDER BY 1, 2, 3, 4";
    public static String SQL_VS_EDNL = "SELECT   iim.item_desc1, loc.loc_name,ilm.lot_no,\n" +
"         ROUND (SUM (trans_qty), 3) qty\n" +
"    FROM apps.ic_tran_vw1@ednl_apps itv,\n" +
"         apps.ic_item_mst@ednl_apps iim,\n" +
"         apps.ic_lots_mst@ednl_apps ilm,\n" +
"         mmt_loc_mst loc\n" +
"   WHERE itv.co_code = 'DHJ'\n" +
"     AND itv.orgn_code IN ('DUS', 'US1')\n" +
"     AND iim.item_id = itv.item_id\n" +
"     AND itv.completed_ind = 1\n" +
"     AND iim.noninv_ind = 0\n" +
"     AND itv.item_id = ilm.item_id\n" +
"     AND itv.lot_id = ilm.lot_id\n" +
"     AND itv.LOCATION = loc.locator_name\n" +
"GROUP BY iim.item_desc1, loc.loc_name, ilm.lot_no\n" +
"  HAVING ROUND (SUM (trans_qty), 3) != 0\n" +
"ORDER BY 1, 2, 3, 4";

}
