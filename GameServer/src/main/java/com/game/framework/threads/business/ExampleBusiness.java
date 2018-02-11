package com.game.framework.threads.business;

import org.springframework.stereotype.Component;

/**
 * 例子
 * @author liguorui
 *
 */
@Component
public class ExampleBusiness extends AbstractBusinessService {

	@Override
	public int getProcessNums() {
		return 100;
	}

	@Override
	public void execute() {
	}
	
	public void example() {
		this.pushMessage(new IBusinessMessage() {
			@Override
			public void execute() {
				//do
				System.out.println("测试例子");
			}
		});
	}

}
