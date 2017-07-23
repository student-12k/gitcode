package open;


public class Items {
 private int id;
 private String name;
 private String city;
 private int price;
 private int number;
 private String picture;
 private String cateName;
private String itemDetail;
private int cateId;


public int getCateId() {
	return cateId;
}
public void setCateId(int cateId) {
	this.cateId = cateId;
}
public String getItemDetail() {
	return itemDetail;
}
public void setItemDetail(String itemDetail) {
	this.itemDetail = itemDetail;
}
public Items()
	{
		
	}
 public Items(int id,String name,String city,int price,int number,String picture)
	{
		this.id = id;
		this.name = name;
		this.city = city;
		this.picture = picture;
		this.price = price;
		this.number = number;
	}
public String getCateName() {
	return cateName;
}
public void setCateName(String cateName) {
	this.cateName = cateName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return this.getId()+this.getName().hashCode();
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	if(this==obj)
	{
		return true;
	}
	if(obj instanceof Items)
	{
		Items i = (Items)obj;
		if(this.getId()==i.getId()&&this.getName().equals(i.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

public String toString()
{
	return "��Ʒ��ţ�"+this.getId()+",��Ʒ��ƣ�"+this.getName();
}
}
