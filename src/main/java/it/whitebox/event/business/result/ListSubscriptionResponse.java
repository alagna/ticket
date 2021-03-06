package it.whitebox.event.business.result;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.business.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class ListSubscriptionResponse extends ServiceResult {
	private List<Subscription> subscriptionList = new ArrayList<>();
}
