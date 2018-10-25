<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <title>物控中心</title>
    <link href=/plm/static/css/main.css rel=stylesheet>
</head>
<link rel=stylesheet href=/plm/static/webuploader.css>
<script>
window.sessionStorage.setItem('ports', 'http://192.168.0.188:9888/plm');
</script>
<body>
<div id=app>
    <app user-name="${userName}" upload-url=http://192.168.0.188:8081/fileSystem/uploadFile
         user-img=http://124.160.96.135:8070/appSign/head.png company-name=物控中心 company-english=${companyName}
         company-logo=https://s1.ax1x.com/2018/04/25/ClfYXq.png></app>
</div>
<script type=text/javascript src=/plm/static/js/manifest.js></script>
<script type=text/javascript src=/plm/static/js/vendor.js></script>
<script type=text/javascript src=/plm/static/js/main.js></script>
</body>
</html>