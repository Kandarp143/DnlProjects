<jsp:useBean id="objG" class="kp.beans.data.pojo.MocInitProc" scope="request"/>
<table  class="form_table" >
       <tr>
        <th colspan="7">
            Process Checklist:will this modification increade the potential to any of the items below
        </th>     
    </tr>
    <tr>
        <th>#</th>
        <th>Item</th>
        <th>Yes/No</th>
        <th></th>
        <th>#</th>
        <th>Item</th>
        <th>Yes/No</th>
    </tr>
    <tr>
        <td>01</td>
        <td>Reaction exothermic</td>
        <td>
            <input type="checkbox" id="g1" name="g1" <%=gm.isASelected(objG.getG1())%>/>
        </td>
        <td></td>
        <td>16</td>
        <td>Pressure</td>
        <td>
            <input type="checkbox" id="g16" name="g16" <%=gm.isASelected(objG.getG16())%>/>
        </td>
    </tr>
    <tr>
        <td>02</td>
        <td>Reaction temperature</td>
        <td>
            <input type="checkbox" id="g2" name="g2" <%=gm.isASelected(objG.getG2())%>/>
        </td> <td></td>
        <td>17</td>
        <td>Particle size</td>
        <td>
            <input type="checkbox" id="g17" name="g17" <%=gm.isASelected(objG.getG17())%>/>
        </td>
    </tr>
    <tr>
        <td>03</td>
        <td>Decomposition temperature</td>
        <td>
            <input type="checkbox" id="g3" name="g3" <%=gm.isASelected(objG.getG3())%>/>
        </td> <td></td>
        <td>18</td>
        <td>Sample</td>
        <td>
            <input type="checkbox" id="g18" name="g18" <%=gm.isASelected(objG.getG18())%>/>
        </td>
    </tr>
    <tr>
        <td>04</td>
        <td>Flammability</td>
        <td>
            <input type="checkbox" id="g4" name="g4" <%=gm.isASelected(objG.getG4())%>/>
        </td> <td></td>
        <td>19</td>
        <td>Addition sequence</td>
        <td>
            <input type="checkbox" id="g19" name="g19" <%=gm.isASelected(objG.getG19())%>/>
        </td>
    </tr>
    <tr>
        <td>05</td>
        <td>Toxicity</td>
        <td>
            <input type="checkbox" id="g5" name="g5" <%=gm.isASelected(objG.getG5())%>/>
        </td> <td></td>
        <td>20</td>
        <td>Emission</td>
        <td>
            <input type="checkbox" id="g20" name="g20" <%=gm.isASelected(objG.getG20())%>/>
        </td>
    </tr>
    <tr>
        <td>06</td>
        <td>Product specification</td>
        <td>
            <input type="checkbox" id="g6" name="g6" <%=gm.isASelected(objG.getG6())%>/>
        </td> <td></td>
        <td>21</td>
        <td>Static electricity</td>
        <td>
            <input type="checkbox" id="g21" name="g21" <%=gm.isASelected(objG.getG21())%>/>
        </td>
    </tr>
    <tr>
        <td>07</td>
        <td>Corrosiveness</td>
        <td>
            <input type="checkbox" id="g7" name="g7" <%=gm.isASelected(objG.getG7())%>/>
        </td> <td></td>
        <td>22</td>
        <td>Pressure relieve</td>
        <td>
            <input type="checkbox" id="g22" name="g22" <%=gm.isASelected(objG.getG22())%>/>
        </td>
    </tr>
    <tr>
        <td>08</td>
        <td>Chemical incompatibility</td>
        <td>
            <input type="checkbox" id="g8" name="g8" <%=gm.isASelected(objG.getG8())%>/>
        </td>	 <td></td>
        <td>23</td>
        <td>Decontamination</td>
        <td>
            <input type="checkbox" id="g23" name="g23" <%=gm.isASelected(objG.getG23())%>/>
        </td>
    </tr>
    <tr>
        <td>09</td>
        <td>Sewage</td>
        <td>
            <input type="checkbox" id="g9" name="g9" <%=gm.isASelected(objG.getG9())%>/>
        </td><td></td>
        <td>24</td>
        <td>Agitator / Baffles</td>
        <td>
            <input type="checkbox" id="g24" name="g24" <%=gm.isASelected(objG.getG24())%>/>
        </td>
    </tr>
    <tr>
        <td>10</td>
        <td>Level</td>
        <td>
            <input type="checkbox" id="g10" name="g10" <%=gm.isASelected(objG.getG10())%>/>
        </td><td></td>
        <td>25</td>
        <td>Rotation</td>
        <td>
            <input type="checkbox" id="g25" name="g25" <%=gm.isASelected(objG.getG25())%>/>
        </td>
    </tr>
    <tr>
        <td>11</td>
        <td>pH</td>
        <td>
            <input type="checkbox" id="g11" name="g11" <%=gm.isASelected(objG.getG11())%>/>
        </td> <td></td>
        <td>26</td>
        <td>Vacuum</td>
        <td>
            <input type="checkbox" id="g26" name="g26" <%=gm.isASelected(objG.getG26())%>/>
        </td>
    </tr>
    <tr>
        <td>12</td>
        <td>Backfloe / counterflow</td>
        <td>
            <input type="checkbox" id="g12" name="g12" <%=gm.isASelected(objG.getG12())%>/>
        </td><td></td>
        <td>27</td>
        <td colspan="2">Others:
            <input type="text" id="g27" name="g27" value="<%=objG.getG27()%>"/>
        </td>
    </tr>
    <tr>
        <td>13</td>
        <td>Utilities</td>
        <td>
            <input type="checkbox" id="g13" name="g13" <%=gm.isASelected(objG.getG13())%>/>
        </td>
    </tr>
    <tr>
        <td>14</td>
        <td>Raw material</td>
        <td>
            <input type="checkbox" id="g14" name="g14" <%=gm.isASelected(objG.getG14())%>/>
        </td>
    </tr>
    <tr>
        <td>15</td>
        <td>Raw material supplier</td>
        <td>
            <input type="checkbox" id="g15" name="g15" <%=gm.isASelected(objG.getG15())%>/>
        </td>
    </tr>

</table>