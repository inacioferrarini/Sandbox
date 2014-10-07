//
//  CalcOperation.swift
//  Calculator
//
//  Created by José Inácio Athayde Ferrarini on 9/3/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import Foundation

public class CalcOperation {	
	
	public func calculateWithArray(anArr:[String]) -> Double {
		
		var total:Double = 0.0
		
//		var varA:Double = 0.0
//		var varB:Double = 0.0
		
		var idx:Int = 0;
		
		for str:String in anArr {
			
//			if (str == "+") {
//				
//			} else if (str == "-") {
//				
//			} else if (str == "/") {
//				
//			} else if (str == "*") {
//				
//			} else {
//				total = (str as NSString).doubleValue
//			}
			idx++;
		}
		
		return total;
	}
	
	
	public func performOperation(op: String, valA: Double, valB: Double) -> Double {
		var res:Double = 0.0
		
		switch op {
			case "+":
				res = valA + valB
			case "-":
				res = valA - valB
			case "*":
				res = valA * valB
			case "/":
				res = valA / valB
			default:
				res = 0
		}
		return res;
	}
	
}