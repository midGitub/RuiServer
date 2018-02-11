package com.game.framework.utils;

public interface IObjectConverter<S, D> {

	public D convert(S s);
}
