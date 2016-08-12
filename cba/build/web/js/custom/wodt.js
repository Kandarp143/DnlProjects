var $lastInput = $('#F');
//For tab looping
$('#F').keydown(function (e) {
    if (e.which === 9 && $lastInput.is(":focus")) {
        $("#btnAdd").focus();
        e.preventDefault();
    }
});
//Add item row
$("#btnAdd").click(function () {
    itms.pop($('#A').val());
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    ctr++;
    var A = "A" + ctr;
    var B = "B" + ctr;
    var C = "C" + ctr;
    var D = "D" + ctr;
    var E = "E" + ctr;
    var F = "F" + ctr;
    var G = "G" + ctr;
    var J = "J" + ctr;
    var I = "I" + ctr;
    var errmsg = "Please Fill Required Fields :";
    var bool = "true";
    if ($('#A').val() === "0") {
        errmsg = errmsg + "Item";
        bool = "false";
    }
    if (!checkdup($('#A').val())) {
        bool = "false";
    }

    if (bool === "false") {
        alert(errmsg);
    } else {
        var newTr = '<tr>\
<td><button type="button" class="removebutton" title="Remove this row">X</button></td>\
<td>' + ctr + '</td>\
<td><input type="hidden" id=' + A + ' name=' + A + '  value="' + $('#A').val() + '" />\
<input type="text"  value="' + $('#A').find('option:selected').text() + '" readonly style="width: 300px" /></td>\
<td><input type="text" id=' + B + ' name=' + B + '  value="' + $('#B').val() + '" readonly size="3" /></td>\
<td><input type="text" id=' + C + ' name=' + C + '  value="' + $('#C').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="text" id=' + D + ' name=' + D + '  value="' + $('#D').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="text" id=' + E + ' name=' + E + '  value="' + $('#C').val() * $('#D').val() + '" readonly size="3" class="cur"/></td>\
<td><input type="hidden" id=' + G + ' name=' + G + '  value="' + $('#G').val() + '" />\
<input type="text"  value="' + $('#G').find('option:selected').text() + '" readonly style="width: 100px" /></td>\\n\
<td><input type="hidden" id=' + I + ' name=' + I + '  value="' + $('#I').val() + '" />\
<input type="text"  value="' + $('#I').find('option:selected').val() + '" readonly style="width: 200px" /></td>\\n\
<td><input type="hidden" id=' + J + ' name=' + J + '  value="' + $('#J').val() + '" />\
<input type="text"  value="' + $('#J').find('option:selected').val() + '" readonly style="width: 100px" /></td>\
<td><input type="text" id=' + F + ' name=' + F + '  value="' + $('#F').val() + '" readonly size="10" /></td>\
</tr>';
        $('#myTable').append(newTr);
        $('#itemrows').val(ctr);
        var iv = $('#A').val();
        itms.push(iv);
    }
});
//Remove button click
$(document).on('click', 'button.removebutton', function () {
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    var v = $(this).closest('td').next('td').text();
    $(this).closest('tr').remove();
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    return false;
});
//Check Item duplication
function  checkdup(itm) {
    var dup = true;
    for (i = 0; i < itms.length; i++) {
        if (itm === itms[i]) {
            alert("Duplicate Item Not Allow");
            dup = false;
        }
    }
    return dup;
}
