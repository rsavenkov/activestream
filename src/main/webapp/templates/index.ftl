<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>City service</title>
    <link href='resources/css/bootstrap.min.css' rel="stylesheet">
    <#--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <#--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
</head>
<body id="body" data-base="<@spring.url "/"/>">

<br/>

<div class="row">
    <div class="col-md-4">
        <div class="input-group">
      <span class="input-group-btn">
        <button id="getData" class="btn btn-default" type="button">Получить данные</button>
      </span>
            <input type="text" class="form-control" placeholder="http://188.166.5.154:8080/micro/service/service.wsdl"
                   readonly>
        </div>
    </div>
    <div id="loader" style="display: none;margin-left: 15px;"><img
            src=""<@spring.url "/"/>/resources/images/ajax-loader.gif"/></div>
</div>

<br/>

<div class="row">
    <div class="col-lg-5">
        <ul id="result" class="list-group">
        </ul>
    </div>
</div>

<script src='resources/js/jquery.min.js'></script>
<script src='resources/js/bootstrap.min.js'></script>
<script type="application/javascript">

    $(document).ready(function () {

        var base = $('#body').attr('data-base');

        $("#getData").click(function () {
            $('#loader').css('display', 'inline');
            $.get("http://jbossews-dttd.rhcloud.com" + base + "getCities")
                    .success(function (response) {
                        if (response.result != undefined) {
                            $(response.result).each(function (i, el) {
                                $("#result").append('<li class="list-group-item">' + i + ' - ' + el + '</li>');
                            })
                        } else {
                            alert("Произошла серверная ошибка. За подробностями в лог")
                        }

                    })
                    .error(function () {
                        alert("Непредвиденная ошибка при запросе данных с сервера")
                    })
            $('#loader').css('display', 'none');
        })
    });

</script>
</body>
</html>