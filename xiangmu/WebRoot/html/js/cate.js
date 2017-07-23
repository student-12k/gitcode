$(function() {
     var ui=document.getElementById("display");
     ui.style.display="none";
});
function doSearch() {
	var ui=document.getElementById("display");
	ui.style.display="";
}
function dodelect(){
	var ui=document.getElementById("info");
	ui.style.display="none";
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


function addcate(){
	window.open('addcate.html','_blank','width=788,height=200,top=200,left=400');
	 setTimeout(copy,1000);	
}
function copy(){
	var result="";
	result = result 
	+"<tr align=\"center\" id=\"info\">"+ "<td colspan=1><input type=\"checkbox\"></td>"+"<td colspan=3  id=\"id\">4</td>"+"<td colspan=3>饮料</td>"+"<td colspan=3 >碳酸饮料</td>";
	$("#display").append(result);
}
function close(){
	window.close();
}