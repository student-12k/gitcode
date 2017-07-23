function doSearch() {
	document.getElementById("checkBoxAll").checked = false;
	// 表格删除
	var table = document.getElementById("myTable");
	document.getElementById("checkBoxAll");
	deleteTable(table);
	// 取得输入框的值
	var data = {
		checkCode : $('#checkCode').val().trim(),
		checkName : $('#checkName').val().trim()
	};

	// 对检索值解码
	var searchKey = encodeURIComponent(JSON.stringify(data));
	$
			.ajax({
				// 传值方式"POST"
				type : "POST",
				// 和前面JAVA Controller中的value一致
				url : "../houtaiservlet/orderitems",
				dataType : "json",
				data : {
					searchKey : searchKey
				},
				success : function(data) {		
			    document.getElementById("msgText").innerHTML = data.msgText;
					// 对获取的值进行字符拼接，并打印出来
					var result = "";
					// 用for循环对表格进行赋值
					for ( var i = 0; i < data.list.length; i++) {
						result = result + "<tr align=\"center\">";
						// 将部门号和工号插入，但是隐藏便于删除操作的完成
						result = result
								+ "<td colspan=1 height=22px  align=\"center\" >"
								+ "<input type=\"checkbox\" name='checkResult' onclick=\"clickCheckBox()\">"+
								"<input type=\"hidden\" name='id' value='"+data.list[i].orderID+"' >"
								+ "</td>";
						// 插入部门号
						result = result
								+ "<td colspan=3 height=22px  align=\"center\"  >"
								+ data.list[i].orderID + "</td>";
						// 插入部门名全称
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.list[i].userid+ "</td>";
						// 插入部门名略称
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.list[i].name + "</td>";
						// 插入职位
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.list[i].phonenumber + "</td>";
						// 插入员工性
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.list[i].totalPrice + "</td>";
						// 插入员工名
						result = result
								+ "<td colspan=3 height=22px  align=\"center\">"
								+ data.list[i].payway + "</td>";
						// 插入员工收入
						result = result
						+ "<td colspan=6 height=22px  align=\"right\">"
						+ data.list[i].creatTime + "</td>";
						result = result
						+ "<td colspan=3 height=22px  align=\"center\">"
						+ data.list[i].orderState + "</td>";
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
				url : "../houtaiservlet/orderitems",
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