function addOrder() {
	var orderID = document.getElementById("orderID").innerText.trim();
	$.ajax({
				url : 'itemservlet/orderservlet',
				type : 'POST',
				dataType : 'json',
				data : {
					username : $("#username").val(),
					address : $("#address").val(),
					phone : $("#phone").val(),
					payway : $("#payway").val(),
					orderID : orderID
				},
				success : function(data) {
					if (data.success == 1) {
						window.open("zhifu.html");
					} else {
						alert("您已经支付了或者未支付成功");
						history.go(0);
					}
				},
				error : function() {

				}
			});
}
