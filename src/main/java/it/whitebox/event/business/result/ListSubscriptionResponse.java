package it.whitebox.event.business.result;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.business.domain.Subscription;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class ListSubscriptionResponse extends ServiceResult {
	private List<Subscription> subscriptionList = new ArrayList<>();
}
