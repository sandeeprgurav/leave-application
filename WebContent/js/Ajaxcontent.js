var xmlHttp;
var designation;

function showContentAjax(strUrl,strArg) { 
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	var strArg1=document.all.source5.value;
	var dept=document.all.source4.value;
    var strArg2="&dept="+dept;
	var url = strUrl+"?";
	if (strArg != "") 
	{ 
	     if(strArg1=="SELECT")
		 url=url + strArg;
		 else
         url=url + strArg + strArg2;
	}
	xmlHttp.open("GET",url,true);	
	xmlHttp.onreadystatechange=stateChanged;
	xmlHttp.send(null);
}


function stateChanged() { 
	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete") {
		if(xmlHttp.status==200) {
			var desiArr= new Array();
			var res=xmlHttp.responseText;
			desiArr=res.split("~");
            var tableRef=null;
			var Ref1=document.all.source5.value;
            var Ref2=document.all.source4.value;
			if(Ref1=="SELECT") {
			    tableRef=document.all.source5;
			} else if(Ref1!="SELECT") {
                tableRef=document.all.leaveappvby;
			}

			for(;tableRef.options.length>0;)
				tableRef.options.remove(0);	
			tableRef.options.add(new Option("Select","-1"));
			for(var i=0;i<desiArr.length;i++) {
				if(desiArr[i].length>0)
					tableRef.options.add(new Option(desiArr[i],desiArr[i]));
				}
		 }
	}
}

function GetXmlHttpObject()
{ 
  var objXMLHttp=null;
  if (window.XMLHttpRequest)
	{
 	  objXMLHttp=new XMLHttpRequest();
	}
  else if (window.ActiveXObject)
	{
	  objXMLHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
  return objXMLHttp;
} 
