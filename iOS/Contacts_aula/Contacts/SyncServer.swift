//
//  SyncServer.swift
//  Contacts
//
//  Created by José Inácio Athayde Ferrarini on 9/10/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import Foundation

protocol SyncServerDelegate: class {
	func infoSaved()
}

class SyncServer: NSObject {
	
	weak var delegate: SyncServerDelegate?
	
	init(delegate:SyncServerDelegate) {
		super.init()
		self.delegate = delegate
	}
	
	func saveInfo() {
		let timer = NSTimer.scheduledTimerWithTimeInterval(3, target: self, selector: "finished", userInfo: nil, repeats: false)
	}
	
	func finished() {
		self.delegate?.infoSaved()
	}
}