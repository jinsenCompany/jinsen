<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <script src="../js/jquery.min.js"></script>

        <script>
          $(document).ready(function () {
            $.getJSON(window.location.href.concat('/status'), function(data) {
              if (data === "created") {

              } else {
                // task is already being executed
                refreshProgress();
              }
            });
          });

          var width = 0;

          function getProgress() {
            $.getJSON(window.location.href.concat('/progress'), function(percentage) {
              $('#progressBar').css('width', percentage+'%');
              document.getElementById("label").innerHTML = percentage * 1 + '%';
              width = percentage;
            });
          }

          function start() {
            $.ajax({
              type: "post",
              data: $('#task').serialize(),
              success: function(data) {
                $('#progressBar').css('width', 100+'%');
                document.getElementById("label").innerHTML = 100 * 1 + '%';

                // do sth with the data after finished task
              }
            });

            width = 0;
            $('#progressBar').css('width', 0+'%');
            document.getElementById("label").innerHTML = 0 * 1 + '%';

            refreshProgress();
          }

          function refreshProgress() {
            $("#btnStart").prop("disabled",true);

            var id = setInterval(frame, 1000);
            function frame() {
                if (width >= 100) {
                    clearInterval(id);
                    $("#btnStart").prop("disabled",false);

                } else {
                    getProgress();
                }
            }
          }

        </script>

    </head>
    <body>

      <div class="container">

        <h2 class="text-center">Progress Bar Example</h2>
        <div class="progress">
          <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:0%">
            <div id="label">0%</div>
          </div>
        </div>

        <form:form method="POST" commandName="task" cssClass="form-horizontal">
        <fieldset>

        <div class="form-group">
          <label class="col-md-4 control-label" for="btnStart">Actions</label>
          <div class="col-md-8">
            <button id="btnStart" name="btnStart" class="btn btn-success">Start</button>
            <button id="btnStop" name="btnStop" class="btn btn-danger">Stop</button>
          </div>
        </div>

        </fieldset>
        </form:form>

      </div>

      <script>
        $('#task').submit(function () {
         start();
         return false;
        });
      </script>

    </body>
</html>>