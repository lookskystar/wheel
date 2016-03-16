<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/system/";
%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>库存报警</title>
	<!--框架必需start-->
	<link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="demo.css">
	<script type="text/javascript" src="../js/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/framework.js"></script>
	<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin"/>
	<!--框架必需end-->
	
	<!--截取文字start-->
	<script type="text/javascript" src="../js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<script type="text/javascript" src="../inout/js/lundui.js"></script>
	<script type="text/javascript">
		function closeDialog(tar,evt){
			var href=tar;
			top.Dialog.open({URL:href,Width:900});
			var event=evt?evt:window.event;
			if(event.stopPropagation){
				event.stopPropagation();
			}else{
				event.cancelBubble = true;
			}
			return false;
			//setTimeout(function(){top.Dialog.close();},50);
		}
		$(function(){
			$.get('system/alarmAction!showJSONAlarm.do?t='+new Date(),function(data){
				var result=eval("("+data+")");
				for(var i=0;i<result.total;i++){
					var d=result.rows[i];
					var p=d._parentId;
					if(p!=null){
						$('#test').treegrid('select',p);
					}
					var node=$('#test').treegrid('getSelected');
					var da=[d];
					$('#test').treegrid('append', {
						parent: (node?node.id:null),
						data: da
					});
				}
			});
			$('#test').treegrid({
				iconCls:'icon-save',
				width:'100%',
				height:380,
				nowrap: false,
				rownumbers: true,
				animate:true,
				collapsible:false,
				/*url:'system/alarmAction!showJSONAlarm.do',*/
				idField:'id',
				treeField:'depotCode',
				frozenColumns:[[
	                {title:'单位',field:'depotCode',width:250,
		                formatter:function(value,row){
		                	if(row.axleType==null){
		                		return row.depotName;
		                	}else{
		                		return row.axleType;
		                	}
		                	return '<span style="color:red">'+value+'</span>';
		                }
	                }
				]],
				columns:[[
					{field:'reason',title:'报警原因',width:200,
						formatter:function(value,row){
							return '<span style="color:red">'+value+'</span>';
						}
					},
					{field:'wheelType',title:'查看详细',width:100,
						formatter:function(value,row){
							if(row.reason!=''){
								return '<a href="javascript:void(0);" onclick="closeDialog(\'./inout/wheelStockAction!simpleStockList.do?depot='+row.depotCode+'\',event)">查看详细</a>';
							}
						}
					}
				]]
			});
		});
	</script>
	
</head>
<body>
	<table id="test" ></table>
<!-- 
<div>
    <table class="tableStyle"  headFixMode="true" useMultColor="true">
        <tr>
            <th width="10%"><span>轴型</span></th>
            <th width="10%"><span>轮型</span></th>
            <th width="10%"><span>单位</span></th>
            <th width="10%"><span>最小库存</span></th>
           	<th width="10%"><span>当前库存</span></th>
           	<th width="10%"><span>不良库存上限</span></th>
            <th width="10%"><span>不良库存</span></th>
            <th width="20%"><span>报警原因</span></th>
        </tr>
    </table>
</div>
<div id="scrollContent" >
    <table class="tableStyle"  useMultColor="true" useCheckBox="true" id="setTabel">
    <c:forEach items="${alarm }" var="obj" varStatus="ind">
    	<tr align="center">
            <td width="10%">${obj.axleType }</td>
            <td width="10%">${obj.wheelType }</td>
            <td width="10%">${obj.depotName }</td>    
            <td width="10%">${obj.minStock }</td>
            <td width="10%">
            	<c:choose>
            		<c:when test="${obj.minStock>obj.inventory}">
            			<font style="color:red;font-weight:bold;">${obj.inventory }</font>
            		</c:when>
            		<c:otherwise>${obj.inventory }</c:otherwise>
            	</c:choose>
            </td>         
            <td width="10%">
            	<c:if test="${obj.maxBadStock<=0 }">未设置</c:if>
            	<c:if test="${obj.maxBadStock>0 }">${obj.maxBadStock }</c:if>
            </td>
            <td width="10%">
            	<c:choose>
            		<c:when test="${obj.maxBadStock>0&&obj.maxBadStock<obj.notGoodNum}">
            			<font style="color:red;font-weight:bold;">${obj.notGoodNum }</font>
            		</c:when>
            		<c:otherwise>${obj.notGoodNum }</c:otherwise>
            	</c:choose>
            </td>
            <td width="20%">
            	<c:if test="${obj.minStock>obj.inventory}" ><font style="color:red;font-weight:bold;">库存不足</font></c:if>
            	<c:if test="${obj.maxBadStock>0&&obj.maxBadStock<obj.notGoodNum}"><font style="color:red;font-weight:bold;">不良库存过多</font></c:if>
            </td>
        </tr>
    </c:forEach>
    </table>
</div>
-->
</body>
</html>