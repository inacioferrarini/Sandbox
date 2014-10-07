//
//  ViewController.swift
//  Contacts
//
//  Created by José Inácio Athayde Ferrarini on 9/10/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate, SyncServerDelegate {
	
	@IBOutlet weak var ageLabel: UILabel!
	@IBOutlet weak var contactTypeLabel: UILabel!
	@IBOutlet weak var contactNameText: UITextField!
	
	override func viewDidLoad() {
		super.viewDidLoad()
		println("View ficará visível")
		// Do any additional setup after loading the view, typically from a nib.
		
//		var nameLabel = UILabel()
//		nameLabel.text = "Nome"
//		nameLabel.frame = CGRectMake(120, 30, 200, 30)
//		self.view.addSubview(nameLabel)
	}
	
	override func viewDidAppear(animated: Bool) {
		super.viewDidAppear(animated)
		println("View carregada na tela")
	}
	
	override func didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning()
		// Dispose of any resources that can be recreated.
	}
	
	@IBAction func stepperChanged(sender: UIStepper) {
		ageLabel.text = "\(Int(sender.value))"
	}
	
	@IBAction func setFormaContato(sender: AnyObject) {
		var actionSheet = UIAlertController (title: "Contato",
			message: "Escolha uma forma de contato",
			preferredStyle: UIAlertControllerStyle.ActionSheet)
		
		actionSheet.addAction(UIAlertAction(title: "Email",
			style: UIAlertActionStyle.Default,
			handler: { _ in
				self.contactTypeLabel.text = "Email"
		}))
		actionSheet.addAction(UIAlertAction(title: "Telefone",
			style: UIAlertActionStyle.Default,
			handler: { _ in
				self.contactTypeLabel.text = "Telefone"
		}))
		
		actionSheet.addAction(UIAlertAction(title: "Cancelar",
			style: UIAlertActionStyle.Cancel,
			handler: nil))
		
		self.presentViewController(actionSheet, animated: true, completion: nil)
	}
	
	@IBAction func saveContact(sender: AnyObject) {
		var actionSheet = UIAlertController (title: "Confirmação",
			message: "Deseja gravar este contato?",
			preferredStyle: UIAlertControllerStyle.Alert)
		
		actionSheet.addAction(UIAlertAction(title: "Sim",
			style: UIAlertActionStyle.Default,
			handler: { _ in
				println("Alert - Sim")
				let sync = SyncServer(delegate: self)
				sync.saveInfo()
		}))
		
		actionSheet.addAction(UIAlertAction(title: "Não",
			style: UIAlertActionStyle.Cancel,
			handler: { _ in
				println("Alert - Não")
		}))
		
		self.presentViewController(actionSheet, animated: true, completion: nil)
	}
	
	
	func textFieldShouldReturn(textField: UITextField) -> Bool {
		contactNameText.resignFirstResponder()
		return false;
	}
	
	func infoSaved() {
		var actionSheet = UIAlertController (title: "Salvo",
			message: "Salvo com Sucesso!",
			preferredStyle: UIAlertControllerStyle.Alert)
		
		actionSheet.addAction(UIAlertAction(title: "OK",
			style: UIAlertActionStyle.Cancel,
			handler: nil))
		
		self.presentViewController(actionSheet, animated: true, completion: nil)
	}
}
