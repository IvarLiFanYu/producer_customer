package com.lfy.producer;

import java.util.LinkedList;

/**
 * @Author: Lifanyu
 * @Description: Table 临界资源
 * @Modified By:
 */
class Table extends LinkedList<Object> {

	private int maxSize;

	//构造函数
	public Table(int maxSize) {
		this.maxSize = maxSize;
	}

	//厨师调用 : 容器中放置食品
	public synchronized void putFood(Food f) {
		while (this.size() >= maxSize) {
			System.out.println("容器已满,请稍等...");
			try
			{
				wait();
			}
			catch (Exception e)
			{
			}
		}
		this.addLast(f);
		System.out.println("["+Thread.currentThread().getName()+"]制作一道["+f.getName()+"],剩余"
				+(this.maxSize-this.size())+"个空位置.");
		notify();
	}
	
	//食客调用 : 容器中取出食物
	public synchronized Food getFood() {
		while (this.size() <= 0) {
			System.out.println("容器为空,请稍等...");
			try {
				wait();
			} catch (Exception e) {
			}
		}
		Food f = (Food)this.getFirst();
		this.remove(f);
		System.out.println("["+Thread.currentThread().getName()+"]消费一道["+f.getName()+"],剩余"+(this.maxSize-this.size())+"个");
		notify();
		return f;
	}

}
