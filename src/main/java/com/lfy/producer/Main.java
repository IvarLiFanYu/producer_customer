package com.lfy.producer;
/**
 * @Author: Lifanyu
 * @Description: Main
 * @Modified By:
 */
class Main
{
	public static void main(String[] args) 
	{
		Table table = new Table(4);
		for (int i = 0; i < 4 ; i++ )
		{
			new Thread(new Chef(table),"厨师"+(i+1)).start();
		}
		for (int i = 0; i < 6 ; i++ )
		{
			new Thread(new Customer(table),"顾客"+(i+1)).start();
		}
	}
}
