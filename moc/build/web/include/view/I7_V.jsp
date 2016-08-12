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
            <%=gm.getViewCheckBox(moc.getMocInitProc().getG1())%>
        </td>
         <td></td>
        <td>15</td>
        <td>Raw material supplier</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG15())%>
        </td>
    </tr>
    <tr>
        <td>02</td>
        <td>Reaction temperature</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitProc().getG2())%>
        </td> <td></td>
        <td>16</td>
        <td>Pressure</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG16())%>
        </td>
    </tr>
    <tr>
        <td>03</td>
        <td>Decomposition temperature</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitProc().getG3())%>
        </td> <td></td>
        <td>17</td>
        <td>Particle size</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG17())%>
        </td>
    </tr>
    <tr>
        <td>04</td>
        <td>Flammability</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitProc().getG4())%>
        </td> <td></td>
        <td>18</td>
        <td>Sample</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG18())%>
        </td>
    </tr>
    <tr>
        <td>05</td>
        <td>Toxicity</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG5())%>
        </td> <td></td>
        <td>19</td>
        <td>Addition sequence</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG19())%>
        </td>
    </tr>
    <tr>
        <td>06</td>
        <td>Product specification</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG6())%>
        </td> <td></td>
        <td>20</td>
        <td>Emission</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG20())%>
        </td>
    </tr>
    <tr>
        <td>07</td>
        <td>Corrosiveness</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG7())%>
        </td> <td></td>
        <td>21</td>
        <td>Static electricity</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG21())%>
        </td>
    </tr>
    <tr>
        <td>08</td>
        <td>Chemical incompatibility</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG8())%>
        </td> <td></td>
        <td>22</td>
        <td>Pressure relieve</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG22())%>
        </td>
    </tr>
    <tr>
        <td>09</td>
        <td>Sewage</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG9())%>
        </td> <td></td>
        <td>23</td>
        <td>Decontamination</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG23())%>
        </td>
    </tr>
    <tr>
        <td>10</td>
        <td>Level</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG10())%>
        </td> <td></td>
        <td>24</td>
        <td>Agitator / Baffles</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG24())%>
        </td>
    </tr>
    <tr>
        <td>11</td>
        <td>pH</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG11())%>
        </td> <td></td>
        <td>25</td>
        <td>Rotation</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG25())%>
        </td>
    </tr>
    <tr>
        <td>12</td>
        <td>Backfloe / counterflow</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG12())%>
        </td> <td></td>
        <td>26</td>
        <td>Vacuum</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG26())%>
        </td>
    </tr>
    <tr>
        <td>13</td>
        <td>Utilities</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG13())%>
        </td> <td></td>
        <td>27</td>
        <td colspan="2">Others:

            <%=moc.getMocInitProc().getG27()%>
        </td>
    </tr>
    <tr>
        <td>14</td>
        <td>Raw material</td>
        <td>

            <%=gm.getViewCheckBox(moc.getMocInitProc().getG14())%>
        </td>
    </tr>

</table>