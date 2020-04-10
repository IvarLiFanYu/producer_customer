package com.lfy.producer;

/**
 * @Author: Lifanyu
 * @Description: Customer
 * @Modified By:
 */
class Customer implements Runnable{

	private Table table;

	// 同 Chef 类中 lock 的作用
	private static volatile boolean lock = false;

	public Customer(Table table) {
		this.table = table;
	}
	
	public void run() {
		while (true) {
			if (!lock) {
				lock = !lock;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				table.getFood();
				lock = !lock;
			}
		}
	}

}