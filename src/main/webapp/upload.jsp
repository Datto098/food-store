<%-- 
    Document   : upload.jsp
    Created on : Nov 28, 2023, 1:20:49 PM
    Author     : nguyendatdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload file </title>
    </head>
    <body>
        <h1>Upload file in servlet</h1>
        <form action="<%=request.getContextPath()%>/uploads" method="post" enctype="multipart/form-data" >
            <label>Upload file : </label>
            <input type="file" name="files" multiple />
            <button type="submit">Upload</button>
        </form>
    </body>
</html>
