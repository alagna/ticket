package it.whitebox.event.frontend.response;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.business.domain.Subscription;
import lombok.Data;

/**
 * List of the created subscriptions
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Data
public class CreateSubscriptionResponse {
	private List<Subscription> subscriptionList = new ArrayList<>();
	private double amountToPay=0.0;
}
