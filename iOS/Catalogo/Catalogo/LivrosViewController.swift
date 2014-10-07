//
//  LivrosViewController.swift
//  Catalogo
//
//  Created by José Inácio Athayde Ferrarini on 9/17/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

internal class LivrosViewController: UIViewController, DetalheViewControllerDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	
	// MARK: - DetalheViewControllerDelegate
	func buttonTappedWithPart(numTapped:Int) {
		print("button tapped")
		
	}
	
	
    // MARK: - Navigation
	@IBAction func buttonTap(sender: UIButton) {
		self.performSegueWithIdentifier("livroToDetalheSegue", sender: sender)
	}
	
	override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
		if (segue.identifier == "livroToDetalheSegue") {
			var vc:DetalheViewController = segue.destinationViewController as DetalheViewController
			vc.itemIdx = sender!.tag
			vc.isFromLivro = true
			vc.delegate = self
		}
	}
	
}
