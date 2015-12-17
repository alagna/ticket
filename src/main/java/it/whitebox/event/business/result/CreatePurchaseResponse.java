package it.whitebox.event.business.result;

import it.whitebox.event.business.domain.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class CreatePurchaseResponse extends ServiceResult {
	private Purchase purchase;
}
