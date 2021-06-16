<%@ page contentType="text/html;charset=UTF-8" %>
<style>
    body{
        /*background-image: url("/user/back.jpeg");*/

        /*background: rgba(0, 0, 0, 0);*/
    }

    body{
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg);
    }
    footer{
        margin-top: auto;
    }

    /*div2{*/
    /*    background-color: rgba(255,255,255,0);*/
    /*}*/

    #zatemnenie {
        background: rgba(102, 102, 102, 0.5);
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        display: none;
    }
    #okno {
        width: 300px;
        height: 50px;
        text-align: center;
        padding: 15px;
        border: 3px solid #0000cc;
        border-radius: 10px;
        color: #0000cc;
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        margin: auto;
        background: #fff;
        z-index:9999;
    }
    #zatemnenie:target {display: block;}
    .close {
        display: inline-block;
        border: 1px solid #0000cc;
        color: #0000cc;
        padding: 0 12px;
        margin: 10px;
        text-decoration: none;
        background: #f2f2f2;
        font-size: 14pt;
        cursor:pointer;
    }
    .close:hover {background: #e6e6ff;}
</style>
