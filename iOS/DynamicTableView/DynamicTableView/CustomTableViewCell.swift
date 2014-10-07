//
//  CustomTableViewCell.swift
//  DynamicTableView
//
//  Created by José Inácio Athayde Ferrarini on 9/24/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

class CustomTableViewCell: UITableViewCell {

	private var mainText:UILabel = UILabel(frame: CGRect(x: 5, y:5, width:190, height:90))
	
    override func awakeFromNib() {
        super.awakeFromNib()
		
		self.mainText.backgroundColor = UIColor.clearColor()
		self.mainText.text = ""
		self.contentView.addSubview(mainText)
		
		let botao:UIButton = UIButton(frame: CGRect(x: 220, y: 35, width: 30, height: 30))
		botao.backgroundColor = UIColor.redColor()
		self.contentView.addSubview(botao)
	}
	
	func setupCustomCell(aDict:Dictionary<String, String>) {
		self.mainText.text = aDict["nome"]
	}
	
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

}
