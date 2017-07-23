package open;

import java.util.ArrayList;
import java.util.List;

public class databean {
private List<Items> resultList=new ArrayList<Items>();
private List<Td_orderitem> list = new ArrayList<Td_orderitem>();
public List<Td_orderitem> getList() {
	return list;
}

public void setList(List<Td_orderitem> list) {
	this.list = list;
}

private String msgText;
public String getMsgText() {
	return msgText;
}

public void setMsgText(String msgText) {
	this.msgText = msgText;
}

public List<Items> getResultList() {
	return resultList;
}

public void setResultList(List<Items> resultList) {
	this.resultList = resultList;
}
}
