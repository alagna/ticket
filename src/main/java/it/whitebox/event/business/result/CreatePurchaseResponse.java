package it.whitebox.event.business.result;

import it.whitebox.event.business.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class CreatePurchaseResponse extends ServiceResult {
	private Purchase purchase;
}
