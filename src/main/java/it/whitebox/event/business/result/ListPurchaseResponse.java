package it.whitebox.event.business.result;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.business.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class ListPurchaseResponse extends ServiceResult {
	private List<Purchase> purchaseList = new ArrayList<>();
}
