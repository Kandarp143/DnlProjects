$("#btnAdd").click(function () {
    ctr++;
    var A = "A" + ctr;
    var B = "B" + ctr;
    var C = "C" + ctr;
    var D = "D" + ctr;
    var E = "E" + ctr;
    var F = "F" + ctr;
    var G = "G" + ctr;
    var H = "H" + ctr;
    var J = "J" + ctr;
    var I = "I" + ctr;
    var errmsg = "";
    var bool = "true";
    if ($('#A').val() === "0") {
        errmsg = errmsg + "\nError: Select Item";
        bool = "false";
    }
    if ($('#G').val() === "0") {
        errmsg = errmsg + "\nError: Select Plant";
        bool = "false";
    }
    if ($('#H').val() === "0") {
        errmsg = errmsg + "\nError: Select Cost Center";
        bool = "false";
    }
    if ($('#C').val() === "0" || $('#C').val() === "0.0" || $('#C').val() === "") {
        errmsg = errmsg + "\nError: Enter Quantity";
        bool = "false";
    }
    var wotyp = $('#j').val();
    if (wotyp === "Project") {
        if ($('#J').val() === "" || $('#J').val() === "-" || $('#J').val() === "0") {
            errmsg = errmsg + "\nError: Select Task";
            bool = "false";
        }
        if ($('#I').val() === "" || $('#I').val() === "-" || $('#I').val() === "0") {
            errmsg = errmsg + "\nError: Select Project";
            bool = "false";
        }

        if (($('#C').val() * $('#D').val()) > $('#bud').val()) {
            errmsg = errmsg + "\nError:Bill Line Value exceed Budget Value";
            bool = "false";
        }
    }
    if (bool === "false") {
        alert(errmsg);
    }
    else {
        var newTr = '<tr>\
<td><button type="button" class="removebutton" title="Remove this row">X</button></td>\
<td>' + ctr + '</td>\
<td><input type="hidden" id=' + A + ' name=' + A + '  value="' + $('#A').val() + '" />\
<input type="text"  value="' + $('#A').find('option:selected').text() + '" readonly style="width: 300px" /></td>\
<td><input type="text" id=' + B + ' name=' + B + '  value="' + $('#B').val() + '" readonly size="2" /></td>\
<td><input type="text" id=' + C + ' name=' + C + '  value="' + $('#C').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="text" id=' + D + ' name=' + D + '  value="' + $('#D').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="text" id=' + E + ' name=' + E + '  value="' + $('#C').val() * $('#D').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="text" id=' + G + ' name=' + G + '  value="' + $('#G').val() + '" readonly style="width: 70px" /></td>\
<td><input type="text" id=' + H + ' name=' + H + '  value="' + $('#H').val() + '" readonly style="width: 70px" /></td>\
<td><input type="text" id=' + I + ' name=' + I + '  value="' + $('#I').val() + '" readonly style="width: 200px" /></td>\
<td><input type="text" id=' + J + ' name=' + J + '  value="' + $('#J').val() + '" readonly style="width: 100px" /></td>\
<td><input type="text" id=' + F + ' name=' + F + '  value="' + $('#F').val() + '" readonly size="5" /></td>\
</tr>';
        $('#myTable').append(newTr);
        $('#itemrows').val(ctr);
    }
});
$(document).on('click', 'button.removebutton', function () {
    var v = $(this).closest('td').next('td').text();
    $(this).closest('tr').remove();
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    return false;
});