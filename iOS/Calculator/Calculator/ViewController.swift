//
//  ViewController.swift
//  Calculator
//
//  Created by José Inácio Athayde Ferrarini on 9/3/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
	
	@IBOutlet weak var resultLabel: UILabel!
	private var calcOp:CalcOperation = CalcOperation()
	private var arrItens:[String] = [String]()
	
	@IBAction func buttonTap(sender: UIButton) {
		if (sender.titleLabel!.text == "CA") {
			self.resultLabel.text = "0.0";
			self.arrItens.removeAll(keepCapacity: false)
		} else if (sender.titleLabel!.text == "=") {
			var result:Double = self.calcOp.calculateWithArray(self.arrItens)
			self.resultLabel.text = "\(result)"
		} else {
			if (self.resultLabel.text == "0.0") {
				self.resultLabel.text = ""
			}
			self.arrItens.append(sender.titleLabel!.text!)
			self.resultLabel.text = self.resultLabel.text! + sender.titleLabel!.text!
		}
	}
	
	override func viewDidLoad() {
		super.viewDidLoad()
		// Do any additional setup after loading the view, typically from a nib.
	}

	override func didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning()
		// Dispose of any resources that can be recreated.
	}


}

