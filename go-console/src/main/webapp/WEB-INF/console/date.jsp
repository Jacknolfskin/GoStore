<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="date">
 <span>现在时间： 
 	<script type="text/javascript">
       var day="";
       var month="";
       var ampm="";
       var ampmhour="";
       var myweekday="";
       var year="";
       mydate=new Date();
       myweekday=mydate.getDay();
       mymonth=mydate.getMonth()+1;
       myday= mydate.getDate();
       year= mydate.getFullYear();
       if(myweekday == 0)
       weekday=" 星期日 ";
       else if(myweekday == 1)
       weekday=" 星期一 ";
       else if(myweekday == 2)
       weekday=" 星期二 ";
       else if(myweekday == 3)
       weekday=" 星期三 ";
       else if(myweekday == 4)
       weekday=" 星期四 ";
       else if(myweekday == 5)
       weekday=" 星期五 ";
       else if(myweekday == 6)
       weekday=" 星期六 ";
       document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday);
      </script>
 </span>
</div> 