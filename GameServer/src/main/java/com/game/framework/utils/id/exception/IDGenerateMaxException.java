package com.game.framework.utils.id.exception;

/**
 * 
 * @author liguorui
 * @date 2015年2月6日 下午4:34:41
 */
public class IDGenerateMaxException extends IDGenerateException {

	private static final long	serialVersionUID	= 550592224545296016L;

	public IDGenerateMaxException(int max) {
		super("IdGenerator has generate to the max:" + max);
	}

}
