/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author 02948
 */
public class Sql {

    /**
     *
     */
    public static String get_Item_all = "SELECT * FROM (SELECT DISTINCT msi.inventory_item_id, msi.segment1 item_code,"
            + "                        msi.description item_desc,"
            + "                        msi.primary_unit_of_measure,"
            + "                        DECODE (msi.inventory_planning_code,"
            + "                                2, 'Yes',"
            + "                                'No'"
            + "                               ) min_max_flag,"
            + "                        (SELECT SUM (oh.transaction_quantity)"
            + "                           FROM mtl_onhand_quantities_detail oh"
            + "                          WHERE msi.inventory_item_id ="
            + "                                             oh.inventory_item_id"
            + "                            AND msi.organization_id = oh.organization_id)"
            + "                                                                  on_hand_qty,"
            + "                        (SELECT COUNT (DISTINCT mmt.transaction_id)"
            + "                           FROM mtl_material_transactions mmt"
            + "                          WHERE mmt.inventory_item_id ="
            + "                                                    msi.inventory_item_id"
            + "                            AND mmt.organization_id = msi.organization_id)"
            + "                                                                          cnt,"
            + "                        (SELECT TO_CHAR"
            + "                                    (MAX (mmt.transaction_date),"
            + "                                     'DD-MON-YYYY'"
            + "                                    )"
            + "                           FROM mtl_material_transactions mmt"
            + "                          WHERE mmt.inventory_item_id = msi.inventory_item_id"
            + "                            AND mmt.organization_id = msi.organization_id)"
            + "                                                                last_trx_date"
            + "                   FROM mtl_system_items_b msi,"
            + "                        org_organization_definitions ood"
            + "                  WHERE msi.organization_id = ood.organization_id"
            + "                    AND msi.organization_id <> 0"
            + "                    AND ood.organization_code = 'NAM'"
            + "                    AND msi.inventory_item_flag = 'Y' "
            + "               ORDER BY msi.description, msi.segment1)"
            + " WHERE cnt > 0";

    /**
     *
     */
    public static String get_Item_By_Grp = "SELECT * FROM (SELECT DISTINCT msi.inventory_item_id, msi.segment1 item_code,"
            + "                        msi.description item_desc,"
            + "                        msi.primary_unit_of_measure,"
            + "                        DECODE (msi.inventory_planning_code,"
            + "                                2, 'Yes',"
            + "                                'No'"
            + "                               ) min_max_flag,"
            + "                        (SELECT SUM (oh.transaction_quantity)"
            + "                           FROM mtl_onhand_quantities_detail oh"
            + "                          WHERE msi.inventory_item_id ="
            + "                                             oh.inventory_item_id"
            + "                            AND msi.organization_id = oh.organization_id)"
            + "                                                                  on_hand_qty,"
            + "                        (SELECT COUNT (DISTINCT mmt.transaction_id)"
            + "                           FROM mtl_material_transactions mmt"
            + "                          WHERE mmt.inventory_item_id ="
            + "                                                    msi.inventory_item_id"
            + "                            AND mmt.organization_id = msi.organization_id)"
            + "                                                                          cnt,"
            + "                        (SELECT TO_CHAR"
            + "                                    (MAX (mmt.transaction_date),"
            + "                                     'DD-MON-YYYY'"
            + "                                    )"
            + "                           FROM mtl_material_transactions mmt"
            + "                          WHERE mmt.inventory_item_id = msi.inventory_item_id"
            + "                            AND mmt.organization_id = msi.organization_id)"
            + "                                                                last_trx_date"
            + "                   FROM mtl_system_items_b msi,"
            + "                        org_organization_definitions ood"
            + "                  WHERE msi.organization_id = ood.organization_id"
            + "                    AND msi.organization_id <> 0"
            + "                    AND ood.organization_code = 'NAM'"
            + "                    AND msi.inventory_item_flag = 'Y' "
            + "                    AND SUBSTR (msi.segment1, 0, 2) = ?"
            + "               ORDER BY msi.description, msi.segment1)"
            + " WHERE cnt > 0";

    /**
     *
     */
    public static String get_deldone_item = "SELECT   c.inventory_item_id, a.segment1, c.description,"
            + "         TO_CHAR (a.act_date, 'DD-MON-YYYY'), a.act_date"
            + "    FROM xx_sys_itm_done a, xx_sys_itm_mst c"
            + "   WHERE c.organization_id = 541 AND a.segment1 = c.segment1"
            + " UNION"
            + " SELECT   c.inventory_item_id, a.segment1, c.description,"
            + "         TO_CHAR (a.act_date, 'DD-MON-YYYY'), a.act_date"
            + "    FROM xx_sys_itm_dt a, xx_sys_itm_mst c"
            + "   WHERE c.organization_id = 541 AND a.segment1 = c.segment1 order by act_date";

    /**
     *
     */
    public static String get_only_del_item = " select distinct a.INVENTORY_ITEM_ID,b.segment1,a.description,"
            + " b.MATCH_ITEM_ID,b.GROUP_ID,to_char(b.act_date,'DD-MON-YYYY'),b.act_date "
            + " from XX_SYS_ITM_DT b ,XX_SYS_ITM_MST a"
            + " where a.segment1 = b.segment1 and a.organization_id = 541 order by b.act_date ";

    /**
     *
     */
    public static String get_item_grp = "SELECT DISTINCT SUBSTR (msi.segment1, 0, 2) grp"
            + "           FROM mtl_system_items_b msi,"
            + "                org_organization_definitions ood"
            + "          WHERE msi.organization_id = ood.organization_id"
            + "            AND msi.organization_id <> 0"
            + "            AND ood.organization_code = 'NAM'"
            + "            AND msi.inventory_item_flag = 'Y'"
            + "            AND  (SELECT COUNT (DISTINCT mmt.transaction_id)"
            + "                   FROM mtl_material_transactions mmt"
            + "                  WHERE mmt.inventory_item_id = msi.inventory_item_id"
            + "                    AND mmt.organization_id = msi.organization_id) >0"
            + " ORDER BY grp";

    /**
     *
     */
    public static String get_done_item = "SELECT distinct c.inventory_item_id, a.segment1, "
            + "                 c.description,to_char(a.act_date,'DD-MON-YYYY'), a.act_date"
            + "               FROM xx_sys_itm_done a LEFT JOIN xx_sys_itm_dt b ON a.segment1 = b.segment1"
            + "               inner join xx_sys_itm_mst c on a.segment1 = c.segment1"
            + "              WHERE b.segment1 IS NULL AND c.organization_id = 541 order by a.act_date";
}
