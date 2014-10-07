//
//  CalculoFatorialTests.swift
//  CalculoFatorialTests
//
//  Created by José Inácio Athayde Ferrarini on 9/7/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit
import XCTest
import CalculoFatorial

class CalculoFatorialTests: XCTestCase {

	func testRecursiveFactorial() {
		let factorialCalculator:FactorialCalculator = RecursiveFactorialCalculator()
		XCTAssertEqual(1, factorialCalculator.factorialOf(0));
		XCTAssertEqual(120, factorialCalculator.factorialOf(5));
		XCTAssertEqual(720, factorialCalculator.factorialOf(6));
		XCTAssertEqual(2432902008176640000, factorialCalculator.factorialOf(20));
	}
	
	func testLoopFactorial() {
		let factorialCalculator:FactorialCalculator = LoopFactorialCalculator()
		XCTAssertEqual(1, factorialCalculator.factorialOf(0));
		XCTAssertEqual(120, factorialCalculator.factorialOf(5));
		XCTAssertEqual(720, factorialCalculator.factorialOf(6));
		XCTAssertEqual(2432902008176640000, factorialCalculator.factorialOf(20));
	}
	
	
}
