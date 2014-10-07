//
//  UICustomTableTableViewController.swift
//  DynamicTableView
//
//  Created by José Inácio Athayde Ferrarini on 9/24/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

class UICustomTableTableViewController: UITableViewController {

	var arrItens:Array<Dictionary<String, String>> = [
		["nome":"Fusca", "cod":"0"],
		["nome":"Fiat 147", "cod":"1"],
		["nome":"Landal", "cod":"2"],
		["nome":"Corsa", "cod":"3"],
		["nome":"Palio", "cod":"4"],
		["nome":"Meriva", "cod":"5"],
		["nome":"Celta", "cod":"6"],
		["nome":"Astra", "cod":"7"],
		["nome":"Porsche", "cod":"8"],
		["nome":"Aston Martin", "cod":"9"]
	]
	
	var arrSections:Array<Array<Dictionary<String, String>>> = Array<Array<Dictionary<String, String>>>()
	
    override func viewDidLoad() {
        super.viewDidLoad()
		
		self.refreshControl = UIRefreshControl()
		self.refreshControl?.addTarget(self, action: "doRefresh", forControlEvents: UIControlEvents.ValueChanged)
		self.refreshControl?.tintColor = UIColor.redColor()
		
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        self.navigationItem.rightBarButtonItem = self.editButtonItem()
		
		
		for (var i=0; i < self.tableView.numberOfSections(); i++) {
			self.arrSections.append(self.getItensToSection(i))
		}
		
    }
	
	func doRefresh() {
		//end refreshing
		self.refreshControl?.endRefreshing()
	}
	
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
		return 2;
		//return self.arrSections.count
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.arrSections[section].count
    }

	override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
		let cell = tableView.dequeueReusableCellWithIdentifier("CustomCellID", forIndexPath: indexPath) as CustomTableViewCell
		
		var secArr:Array<Dictionary<String, String>> = self.arrSections[indexPath.section]
		let dict = secArr[indexPath.row]
		
		cell.setupCustomCell(dict)
//		cell.textLabel?.text = dict["nome"]
//		cell.imageView?.image = UIImage(named: "carro")
//		cell.detailTextLabel?.text = "Texto Secundario"
//		cell.accessoryType = UITableViewCellAccessoryType.DetailDisclosureButton
		return cell
	}
	
	override func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
		return CGFloat(100)
	}
	
	override func tableView(tableView: UITableView, accessoryButtonTappedForRowWithIndexPath indexPath: NSIndexPath) {
		
		if (1 == indexPath.row) {
			return
		}
		
		let alert:UIAlertController = UIAlertController(title: "Accessory Tap", message: "Índice \(indexPath.row)", preferredStyle: UIAlertControllerStyle.Alert)
		
		alert.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.Default, handler: nil))
		self.presentViewController(alert, animated: true, completion: nil)
	}
	
	override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
		self.performSegueWithIdentifier("toDetailSegue", sender: self)
	}
	
	// MARK: - Header
	override func tableView(tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
		let header:UIImageView = UIImageView(image: UIImage(named: "header"))
		return header
	}
	
	override func tableView(tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
		return 50
	}
	
	// MARK: - Footer
	override func tableView(tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
		let header:UIImageView = UIImageView(image: UIImage(named: "footer"))
		return header
	}
	
	override func tableView(tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
		return 50
	}
	
	// MARK: - Edit Mode
	override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
		
		tableView.beginUpdates()
		if (UITableViewCellEditingStyle.Delete == editingStyle) {
			self.arrSections[indexPath.section].removeAtIndex(indexPath.row)
			tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: UITableViewRowAnimation.Automatic)
		}
		tableView.endUpdates()
	}
	
	
	// MARK: - Utilities
	private func getItensToSection(aSectionNum:Int) -> Array<Dictionary<String, String>> {
		var arrRet:Array<Dictionary<String, String>> = Array<Dictionary<String, String>>()
		
		if (aSectionNum == 0) {
			for dic in self.arrItens {
				if (dic["cod"]?.toInt() < 4) {
					arrRet.append(dic)
				}
			}
		} else {
			for dic in self.arrItens {
				if (dic["cod"]?.toInt() >= 4) {
					arrRet.append(dic)
				}
			}
		}
		return arrRet
	}
	
}
