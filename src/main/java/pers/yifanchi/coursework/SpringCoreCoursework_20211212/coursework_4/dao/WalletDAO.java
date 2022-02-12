package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;

public interface WalletDAO {
	Wallet queryWalletById(Integer wid) throws Exception;
	Wallet queryWalletByName(String wname) throws Exception;
	Integer insertWalletGetWid(Wallet wallet) throws Exception;
}
