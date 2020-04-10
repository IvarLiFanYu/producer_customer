package com.lfy.producer;

/**
 * @Author: Lifanyu
 * @Description: Chef
 * @Modified By:
 */
class  Chef implements Runnable
{	
	//菜名
	private static final String DISH= "麻婆豆腐,回锅肉,红烧肉,宫保鸡丁,"+
									  "酸菜鱼,鱼香肉丝,糖酥排骨,小葱拌豆腐,拍黄瓜,冰镇酸辣蜇头,凉拌裙带菜";
	/**
	 * 解决 synchronized 的可重入性, 每个线程第二次执行 putFood() 都要改变 lock 状态
	 * 且保证多个线程得到最新的 lock 数据
	 */
	private static volatile boolean lock = false;

	private Table table;

	public Chef(Table table) {
		this.table = table;
	}

	public void run() {
		// 如果没有 lock 在 while 中会出现 synchronized 可重入性错误
		while (true) {
			if (!lock) {
				lock = !lock;
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Food f = new Food(DISH.split(",")[(int)(Math.random()*DISH.split(",").length)]);
				table.putFood(f);
				lock = !lock;
			}
		}
	}

}
