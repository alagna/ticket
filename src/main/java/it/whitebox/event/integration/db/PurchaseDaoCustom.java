package it.whitebox.event.integration.db;

import java.util.Date;
import java.util.List;

import it.whitebox.event.business.domain.Purchase;

public interface PurchaseDaoCustom {
	public List<Purchase> findByDate(Date startDate, Date endDate);
}
