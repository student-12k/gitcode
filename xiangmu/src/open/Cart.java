package open;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;



public class Cart {
//���ﳵ����Ʒ����
private HashMap<Items,Integer> goods;
//���ﳵ��Ʒ���ܼ۸�
private double totalprice;
//����
public Cart(){
	goods=new HashMap<Items,Integer>();
	totalprice=0.0;
}
//��ݷ�װ
public HashMap<Items, Integer> getGoods() {
	return goods;
}
public void setGoods(HashMap<Items, Integer> goods) {
	this.goods = goods;
}
public double getTotalprice() {
	return totalprice;
}
public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
}
//�����Ʒ���ﳵ�ķ���
	public boolean addGoodsInCart(Items item ,int number)
	{
		if(goods.containsKey(item))
		{
			goods.put(item, goods.get(item)+number);
		}
		else
		{
			goods.put(item, number);	
		}
		calTotalPrice(); //���¼��㹺�ﳵ���ܽ��
		return true;
	}
	
	//ɾ����Ʒ�ķ���
	public boolean removeGoodsFromCart(Items item)
	{
		goods.remove(item);
		calTotalPrice(); //���¼��㹺�ﳵ���ܽ��
		return true;
	}
	
	//ͳ�ƹ��ﳵ���ܽ��
	public double calTotalPrice()
	{
		double sum = 0.0;
		Set<Items> keys = goods.keySet(); 
		Iterator<Items> it = keys.iterator(); 
	    while(it.hasNext())
	    {
	    	Items i = it.next();
	    	sum += i.getPrice()* goods.get(i);
	    }
	    this.setTotalprice(sum); 
	    return this.getTotalprice();
	}
	
	/*public static void main(String[] args) {
		
		//�ȴ���������Ʒ����
		Items i1 = new Items(1,"��������Ь","����",200,500,"001.jpg");
		Items i2 = new Items(2,"�����˶�Ь","����",300,500,"002.jpg");
		Items i3 = new Items(1,"��������Ь","����",200,500,"001.jpg");
		
		Cart c = new Cart();
		c.addGoodsInCart(i1, 1);
		c.addGoodsInCart(i2, 2);
		//�ٴι�����������Ь������3˫
		c.addGoodsInCart(i3, 3);
		
		
		//����������Ʒ�ļ���
		Set<Map.Entry<Items, Integer>> items= c.getGoods().entrySet();
		
		for(Map.Entry<Items, Integer> obj:items)
		{
			System.out.println(obj);
		}
		
		
		System.out.println("���ﳵ���ܽ�"+c.getTotalprice());
		
	}
	*/
}
