package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.dao.WalletDAO;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.entity.Wallet;
import pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception.ServiceException;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	private WalletDAO walletDAO;

	@Override
	public Wallet queryWalletByName(String Name) throws Exception {
		Wallet wallet = walletDAO.queryWalletByName(Name);
		if (wallet==null)
			throw new ServiceException(String.format("There is not a wallet named %s", Name));
		return wallet;
	}

	@Override
	public Integer insertWalletGetWid(Wallet wallet) throws Exception {
		Wallet walletInDB = walletDAO.queryWalletByName(wallet.getWname());
		if(walletInDB!=null)
			throw new ServiceException("There has been already a wallet in the system");
		return walletDAO.insertWalletGetWid(wallet);
	}



}
