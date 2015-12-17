package it.whitebox.event.business.result;

import it.whitebox.event.business.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor
public class CreateSubscriptionResponse extends ServiceResult {
	private Subscription subscription;
}
