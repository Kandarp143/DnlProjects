$(function () {
    //groupTable method has 3 arguments:
    //$rows: jQuery object of table rows to be grouped
    //startIndex: index of first column to be grouped
    //total: total number of columns to be grouped
    function groupTable($rows, startIndex, total) {
        if (total === 0) {
            return;
        }
        //alert("called");
        var i, currentIndex = startIndex, count = 1, lst = [];
        var tds = $rows.find('td:eq(' + currentIndex + ')');
        var ctrl = $(tds[0]);
        lst.push($rows[0]);
        for (i = 1; i <= tds.length; i++) {
            if (ctrl.text() == $(tds[i]).text()) {
                count++;
                $(tds[i]).addClass('deleted');
                lst.push($rows[i]);
            }
            else {
                if (count > 1) {
                    ctrl.attr('rowspan', count);
                    groupTable($(lst), startIndex + 1, total - 1)
                }
                count = 1;
                lst = [];
                ctrl = $(tds[i]);
                lst.push($rows[i]);
            }
        }
    }
    groupTable($('#tt tr:has(td)'), 0, 8);
    $('#tt .deleted').remove();
    groupTable($('#tt2 tr:has(td)'), 0, 8);
    $('#tt2 .deleted').remove();
    groupTable($('#tt3 tr:has(td)'), 0, 8);
    $('#tt3 .deleted').remove();
    groupTable($('#tt4 tr:has(td)'), 0, 8);
    $('#tt4 .deleted').remove();
    groupTable($('#tt5 tr:has(td)'), 0, 8);
    $('#tt5 .deleted').remove();
    groupTable($('#tt6 tr:has(td)'), 0, 8);
    $('#tt6 .deleted').remove();
        groupTable($('#tt7 tr:has(td)'), 0, 8);
    $('#tt7 .deleted').remove();

});
