//
//  DetalheViewController.swift
//  Catalogo
//
//  Created by José Inácio Athayde Ferrarini on 9/17/14.
//  Copyright (c) 2014 José Inácio Athayde Ferrarini. All rights reserved.
//

import UIKit

protocol DetalheViewControllerDelegate: class {
	func buttonTappedWithPart(numTapped:Int)
}

internal class DetalheViewController: UIViewController {

	// MARK: - Outlets
	@IBOutlet weak var closeButton: UIButton!
	@IBOutlet weak var tituloLabel: UILabel!
	@IBOutlet weak var itemTituloLabel: UILabel!
	@IBOutlet weak var part1Button: UIButton!
	@IBOutlet weak var part2Button: UIButton!
	@IBOutlet weak var itemImage:UIImageView!
	
	internal var itemIdx:Int = 0
	internal var isFromLivro:Bool = false
	
	internal weak var delegate:DetalheViewControllerDelegate?
	
	private var livrosArr:[String] = ["Futebol - Uma Janela para o Brasil", "A Terra dos Sonhos", "Manga", "Como se faz"]
	private var filmesArr:[String] = ["2012", "Avatar", "Thor", "Underworld"]
	
    override func viewDidLoad() {
        super.viewDidLoad()
		
		if (self.isFromLivro) {
			self.closeButton.hidden = false
			self.part1Button.hidden = true
			self.part2Button.hidden = true
			self.tituloLabel.text = "Livro"
			self.itemTituloLabel.text = livrosArr[self.itemIdx]
			// TODO: o nome da imagem pode ser passado por parametro ...
			self.itemImage.image = UIImage(named: "Livro\(self.itemIdx+1)")
		} else {
			self.closeButton.hidden = true
			self.tituloLabel.text = "Filme"
			self.itemTituloLabel.text = filmesArr[self.itemIdx]
			// TODO: o nome da imagem pode ser passado por parametro ...
			self.itemImage.image = UIImage(named: "filme\(self.itemIdx+1)")
		}
		
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

	@IBAction func closeTap(sender: AnyObject) {
		self.dismissViewControllerAnimated(true, completion:nil)
	}
	
	@IBAction func buttonPartTap(sender: UIButton) {
		self.navigationController?.popToRootViewControllerAnimated(true)
		self.delegate?.buttonTappedWithPart(sender.tag)
	}
	
	
	
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
