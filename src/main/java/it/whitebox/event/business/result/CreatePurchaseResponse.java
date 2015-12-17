package it.whitebox.event.business.result;

import it.whitebox.event.business.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor
public class CreatePurchaseResponse extends ServiceResult {
	private Purchase purchase;
}
