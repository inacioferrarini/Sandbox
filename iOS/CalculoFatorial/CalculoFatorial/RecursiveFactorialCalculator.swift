//
//  RecursiveFactorialCalculator.swift
//  CalculoFatorial
//
//  Created by José Inácio Athayde Ferrarini on 9/7/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import Foundation

public class RecursiveFactorialCalculator : FactorialCalculator {
	
	public init() {
		
	}
	
	public func factorialOf(n: Int64) -> Int64 {
		return n == 0 ? 1 : n * factorialOf(n - 1)
	}
	
}