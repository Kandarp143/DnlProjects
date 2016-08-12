<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <%
        int can_cid = Integer.parseInt(request.getParameter("cid"));
        int can_sid = 0;
        if (request.getParameterMap().containsKey("sid")) {
            can_sid = Integer.parseInt(request.getParameter("sid"));
        }
        int stg = 0;
        switch (can_sid) {
            case 1:
            case 2:
                stg = 0;
                break;
            case 3:
                stg = 1;
                break;
            case 4:
            case 5:
                stg = 2;
                break;
            case 6:
            case 7:
                stg = 3;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                stg = 4;
                break;
            case 14:
            case 15:
                stg = 5;
                break;
            case 16:
                stg = 6;
                break;
            case 17:
                stg = 7;
                break;
            case 18:
                stg = 8;
                break;
            case 19:
                stg = 9;
                break;
            default:
                stg = 10;
        }
    %>
    <body>
        <canvas id="myCanvas" width="1150">
            HTML5 Canvas not supported
        </canvas>
        <canvas id="myCanvas2" width="1150" height="30" style=" border: 1px solid #C0C0C0;">
            HTML5 Canvas not supported
        </canvas>
        <script>
            var drawing = document.getElementById("myCanvas2");
            var con = drawing.getContext("2d");
            var size = 15;

            //Notation 1 
            con.strokeStyle = "grey";
            con.fillStyle = "black";
            con.fillRect(8, 8, size, size);
            con.strokeRect(8, 8, size, size);
            con.fillStyle = "#292628";
            con.font = "bold 11px Verdana";
            con.fillText("Visited / Completed", 30, 20);

            //Notation 2 
            con.strokeStyle = "grey";
            con.fillStyle = "green";
            con.fillRect(300, 8, size, size);
            con.strokeRect(300, 8, size, size);
            con.fillStyle = "#292628";
            con.font = "bold 11px Verdana";
            con.fillText("Current Stage", 320, 20);

            //Notation 2 
            con.strokeStyle = "grey";
            con.fillStyle = "#FBB917";
            con.fillRect(700, 8, size, size);
            con.strokeRect(700, 8, size, size);
            con.fillStyle = "#292628";
            con.font = "bold 11px Verdana";
            con.fillText("Not Visited / In Progress ", 720, 20);

        </script>
        <script>
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            var sx = 0;
            var sy = 15;
            var cx = -60;
            var cy = 0;
            var rects = 30;
            var arrs = 76;
            var stg = ["MOC request", "Production Head Approval", "TS Department", "HSE Department",
                "ENG Department", "QC Department", "Unit Head \t\t\t\t\t\t\t\t\t\tApproval", "Safety Head \t\t\t\t\tApproval",
                "VP Technology", "VP operation", "Case \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Complete"];
            var stgc = <%=stg%>;

            var i;
            for (i = 0; i <= stg.length; i++) {
                if (i < stgc) {
                    var col = "black";
                    var stro = "black";
                } else if (i > stgc)
                {
                    var col = "#FBB917";
                    var stro = "blue";
                }
                else {


                    if (i == 10) {
                        var col = "black";
                        var stro = "black";
                    } else {
                        var col = "green";
                        var stro = "blue";
                    }

                }
                if (i != 11) {
                    //   alert("I : "+i +"stage : "+stg[i]);
                    cx = get_x(cx, arrs);
                    cy = get_y(cy, arrs, "notarrow");
                    stage(ctx, cx, cy, rects, stg[i], col);
                    if (i != 10) {
                        //     alert("I : "+i +"arrow : "+stg[i]);
                        cx = get_x(cx, rects);
                        cy = get_y(cy, rects, "arrow");
                        arrow(ctx, cx, cy, cx + arrs, cy, stro);
                    } else {
                    }
                }

                else {
                }
            }

            function get_x(cx, s) {
                return cx + s;
            }
            function get_y(cy, s, code) {
                var temp = 0;
                if (code == "arrow") {
                    temp = sy + s / 2;
                }
                else
                {
                    temp = sy;
                }
                return temp;
            }
            function stage(context, fromx, fromy, size, text, color) {
                ctx.fillStyle = color;
                ctx.strokeStyle = "grey";
                ctx.fillRect(fromx, fromy, size, size);
                ctx.strokeRect(fromx, fromy, size, size);
                ctx.font = "bold 11px Verdana";
                ctx.fillStyle = "black";
                // ctx.fillText(text, fromx,fromy+size+15);
                printAt(ctx, text, fromx, fromy + size + 15, 15, 105);
            }
            function printAt(context, text, x, y, lineHeight, fitWidth)
            {
                fitWidth = fitWidth || 0;
                if (fitWidth <= 0)
                {
                    context.fillText(text, x, y);
                    return;
                }
                for (var idx = 1; idx <= text.length; idx++)
                {
                    var str = text.substr(0, idx);
                    console.log(str, context.measureText(str).width, fitWidth);
                    if (context.measureText(str).width > fitWidth)
                    {
                        context.fillText(text.substr(0, idx - 1), x, y);
                        printAt(context, text.substr(idx - 1), x, y + lineHeight, lineHeight, fitWidth);
                        return;
                    }
                }
                context.fillText(text, x, y);
            }
            function arrow(context, fromx, fromy, tox, toy, color) {
                ctx.beginPath();
                var headlen = 10;	// length of head in pixels
                var dx = tox - fromx;
                var dy = toy - fromy;
                ctx.strokeStyle = color;
                var angle = Math.atan2(dy, dx);
                context.moveTo(fromx, fromy);
                context.lineTo(tox, toy);
                context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
                context.moveTo(tox, toy);
                context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
                ctx.stroke();
            }
        </script>
    </body>
</html>
