package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;

public interface WalletService {
	Wallet queryWalletByName(String Name) throws Exception;
	Integer insertWalletGetWid(Wallet wallet) throws Exception;
}
