package action;

import open.Td_item;
import open.Td_orderitem;

public class itemaction {
 private Td_item tditem=new Td_item();
 private Td_orderitem userorder=new Td_orderitem();

public Td_item getTditem() {
	return tditem;
}
public void setTditem(Td_item tditem) {
	this.tditem = tditem;
}
public Td_orderitem getUserorder() {
	return userorder;
}
public void setUserorder(Td_orderitem userorder) {
	this.userorder = userorder;
}
 
}
