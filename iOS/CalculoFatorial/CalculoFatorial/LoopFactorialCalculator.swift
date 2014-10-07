//
//  LoopFactorialCalculator.swift
//  CalculoFatorial
//
//  Created by José Inácio Athayde Ferrarini on 9/7/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import Foundation

public class LoopFactorialCalculator : FactorialCalculator {
	
	public init() {
		
	}
	
	public func factorialOf(n: Int64) -> Int64 {
		var res: Int64 = 1
		for (var k:Int64 = n; k>1; k--) {
			res = res * k
		}
		return res
	}
	
}