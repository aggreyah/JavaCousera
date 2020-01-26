package com.aggreyah.recursivefactorial;

import java.math.BigInteger;

public class RecurisiveFactorial {
	
	public static BigInteger factorial(BigInteger aNum) {
		if (aNum.equals(BigInteger.ZERO) || aNum.equals(BigInteger.ONE))
			return BigInteger.ONE;
		else
			return aNum.multiply(factorial(aNum.subtract(BigInteger.ONE)));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(BigInteger.valueOf(50)));
	}
}
