$("#txAdd").click(function () {
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    ctrr++;
    var T = "T" + ctrr;
    var R = "R" + ctrr;
    var errmsg = "Please Fill Required Fields :";
    var bool = "true";
    if ($('#T').val() === "0") {
        errmsg = errmsg + "Tax";
        bool = "false";
    }
    if (bool === "false") {
        alert(errmsg);
    }
    else
    {
        //for tax adhoc
        var ans = $('#T').find('option:selected').data('rate');
        if (ans === null) {
            ans = $('#R').val();
        } else {
            ans = $('#T').find('option:selected').data('rate');
        }

        var newTrr = '<tr>\
   <td><button type="button" class="removebutton2" title="Remove this row">X</button></td>\
   <td>' + ctrr + '</td>\
   <td><input type="hidden" id=' + T + ' name=' + T + '  value="' + $('#T').val() + '" readonly style="width: 350px"/>\
        <input type="text"  value="' + $('#T').find('option:selected').text() + '" readonly style="width: 350px" /></td>\
   <td><input type="text" id=' + R + ' name=' + R + '  value="' + ans + '" readonly size="5"   /></td>\
   </tr>';
        $('#txtable').append(newTrr);
        $('#txrows').val(ctrr);
    }
});
$(document).on('click', 'button.removebutton2', function () {
    var v2 = $(this).closest('td').next('td').text();
    $(this).closest('tr').remove();
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', true);
    return false;
});

$("#calculate").click(function () {
    //clear all field to 0 
    var vans = 0;
    var trate = 0;
    var tans = 0;
    $('#val').val("0");
    $('#apt').val("0");
    $('#toval').val("0");
    //calculate total value
    for (var i = 1; i <= ctr; i++) {
        if (typeof $('#C' + i).val() !== 'undefined') {
            if (parseFloat($('#C' + i).val()) !== 'NaN') {
                var vans = parseFloat($('#C' + i).val()) * parseFloat($('#D' + i).val());
                var vans2 = parseFloat($('#val').val()) + vans;
                $('#E' + i).val(vans.toFixed(2));
                $('#val').val(vans2.toFixed(2));
            }
        }
    }
    //apply total tax
    for (var i = 1; i <= ctrr; i++) {
        if (typeof $('#R' + i).val() !== 'undefined') {
            trate = parseFloat(trate) + parseFloat($('#R' + i).val());
        }
    }
    tans = parseFloat($('#val').val()) * (parseFloat(trate) / 100);
    $('#apt').val(tans.toFixed(2));
    var final = parseFloat($('#apt').val()) + parseFloat($('#val').val());
    $('#toval').val(final.toFixed(2));

    //submit enable
    var $submit = $('input[type="submit"]');
    $submit.prop('disabled', false);
});
