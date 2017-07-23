function doSearch() {
	location.hash = "";
	document.getElementById("checkBoxAll").checked = false;
	// 表格删除
	var table = document.getElementById("myTable");
	document.getElementById("checkBoxAll");
	deleteTable(table);
	// 取得输入框的值
	var data = {
		checkCode : $('#checkCode').val().trim(),
		checkName : $('#checkName').val().trim(),
		checkcity : $('#checkcity').val().trim()
	};

	// 对检索值解码
	var searchKey = encodeURIComponent(JSON.stringify(data));
	location.hash += "BACK";
	$
			.ajax({
				// 传值方式"POST"
				type : "POST",
				// 和前面JAVA Controller中的value一致
				url : "../houtaiservlet/itemservlet",
				dataType : "json",
				data : {
					searchKey : searchKey
				},
				success : function(data) {		
			    document.getElementById("msgText").innerHTML = data.msgText;		
					// 对获取的值进行字符拼接，并打印出来
					var result = "";
					// 用for循环对表格进行赋值
					for ( var i = 0; i < data.resultList.length; i++) {
						result = result + "<tr align=\"center\">";
						// 将部门号和工号插入，但是隐藏便于删除操作的完成
						result = result
								+ "<td colspan=1 height=22px  align=\"center\" >"
								+ "<input type=\"checkbox\" name='checkResult' onclick=\"clickCheckBox()\">"+
								"<input type=\"hidden\" name='id' value='"+data.resultList[i].id+"' >"
								+ "</td>";
						// 插入部门号
						result = result
								+ "<td colspan=3 height=22px  align=\"center\"  >"
								+ data.resultList[i].id + "</td>";
						// 插入部门名全称
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].name + "</td>";
						// 插入部门名略称
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].city + "</td>";
						// 插入职位
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].price + "</td>";
						// 插入员工性
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].number + "</td>";
						// 插入员工名
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].picture + "</td>";
						// 插入员工收入
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.resultList[i].cateName + "</td>";
						result = result + "</tr>";
					}
					$("#myTable").append(result);

				},
				// 调用失败时执行
				error : function(data) {
					window.alert("error");
				}
			});
}

function deleteTable(table) {
	// 删除表格
	for ( var i = 1; i < table.rows.length; i++) {
		table.deleteRow(i);
		i = i - 1;
	}
}

function clickCheckBox() {
	var checkAll = document.getElementById("checkBoxAll");
	var checkBox = document.getElementsByName("checkResult");
	for ( var i = 0; i < checkBox.length; i++) {
		if (!checkBox[i].checked) {
			checkAll.checked = false;
			return;
		}
	}
	checkAll.checked = true;
}

function doSetAllCheckbox() {
	var checkFash = document.getElementById("checkBoxAll").checked;
	// 获取所有为input属性名标签
	var obj = document.getElementsByTagName("input");
	// 循环赋值
	for ( var i = 0; i < obj.length; i++) {
		// 判断所有标签类型是否为checkbox
		if (obj[i].type == 'checkbox') {
			// 勾选选择框
			obj[i].checked = checkFash;
		}
	}
}

function doDelete() {
	document.getElementById("checkBoxAll");
	var obj = document.getElementsByName("checkResult");
	var j = obj.length;
	var num = 0;
	var ids = document.getElementsByName("id");
	var deleteId = "";
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			// 获取第一个被选中的复选框
			if (num == 0) {
				j = i;
				deleteId = ids[i].value;
				
			} else {
				// 将值用","隔开
				deleteId =deleteId+ "," +ids[i].value;;
			}
			num++;
		}
	}
	// 没有一个复选框被选中
	if (j == obj.length) {
		alert("当前您未选择任一条记录，无法执行删除操作！");
		return;
	}
	$
			.ajax({
				type : "get",
				// 和前面JAVA Controller中的value一致
				url : "../houtaiservlet/itemservlet",
				dataType : "json",
				data : {
					deleteId : deleteId
				},
				success : function(data) {
					doSearch();
				},
				// 调用失败时执行
				error : function(data) {
					window.alert("error");
				}
			});
}
function additem(){
	 window.open('additem.html','_blank','width=788,height=400,top=200,left=400');
	 setTimeout(copy,1000);	
}
function copy(){
	var result="";
	result = result + "<tr align=\"center\">";
	result = result
	+ "<td colspan=1 height=22px  align=\"center\" >"
	+ "<input type=\"checkbox\" name='checkResult' onclick=\"clickCheckBox()\"></td>";
	// 插入部门号
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">12</td>";
	// 插入部门名全称
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">test</td>";
	// 插入部门名略称
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">test</td>";
	// 插入职位
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">11</td>";
	// 插入员工性
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">11</td>";
	// 插入员工名
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">001.jpg</td>";
	// 插入员工收入
	result = result
			+ "<td colspan=3 height=22px  align=\"center\">鞋</td>";
	result = result + "</tr>";
$("#myTable").append(result);
}
function updateitem(){
	var obj = document.getElementsByName("checkResult");
	/** 辅助变量 */
	var j = obj.length;
	var num = 0;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			// 获取第一个被选中的复选框，将该排序值i赋值给j
			if (num == 0) {
				j = i;
			} else {
				alert("您不能同时修改多条数据！！");
				return;
			}
			num++;
		}
	}
	// 没有一个复选框被选中
	if (j == obj.length) {
		alert("当前您未选择任一条记录，无法执行更新操作！");
		return;
	}
	 window.open('upitem.html','_blank','width=300,height=400,top=200,left=200');
}
