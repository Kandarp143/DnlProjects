function checkP(i) {
    var p_checked = '#P' + i + ':checked';
    var pid = '#P' + i;
    var qid = '#Q' + i;
    var rid = '#R' + i;
    if ((($(p_checked).length) > 0)) {
        $(qid).removeAttr('disabled');
        $(rid).removeAttr('disabled');
        $(rid).datepicker('enable');

        $(rid).datepicker({
            showOn: 'button',
            buttonText: 'Show Date',
            buttonImageOnly: true,
            buttonImage: 'image/calendar.gif',
            dateFormat: 'mm/dd/yy',
            constrainInput: true
        });

        $(rid).val($.datepicker.formatDate("mm/dd/yy", new Date()));
    } else {
        $(qid).attr('disabled', 'disabled');
        $(rid).attr('disabled', 'disabled');
        $(rid).datepicker('disable');
        $(p_checked).parent().show();
    }
}