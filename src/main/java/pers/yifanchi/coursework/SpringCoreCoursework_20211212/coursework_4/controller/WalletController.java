package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ControllerException;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service.WalletService;

@Controller
public class WalletController {
	@Autowired
	private WalletService walletService;
	public Integer queryWidByName(String name) {
		Integer wid = 0;
		try {
			Wallet wallet = walletService.queryWalletByName(name);
			wid = wallet.getWid();
		} catch (Exception e) {}
		return wid;
	}
	public Integer insertWallet(String wname, int money) {
		Integer wid = 0;		
		try {
			if (money<0)
				throw new ControllerException("Invalid money amount. Please input Integer larger than zero");
		} catch (ControllerException e) {
			System.out.println(e.getMessage());
		}
		Wallet wallet = new Wallet(wname, money);
		try {
			wid = walletService.insertWalletGetWid(wallet);
		} catch (Exception e) {}
		return wid;
	}
}
