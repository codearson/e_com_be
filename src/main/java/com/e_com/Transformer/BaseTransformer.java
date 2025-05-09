package com.e_com.Transformer;

public interface BaseTransformer<T, I> {

	I transform(T type);

	T reverseTransform(I type);

}
