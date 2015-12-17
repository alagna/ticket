package it.whitebox.event.business.result;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.business.domain.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class ListPurchaseResponse extends ServiceResult {
	private List<Purchase> purchaseList = new ArrayList<>();
}
