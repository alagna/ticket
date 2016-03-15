package it.whitebox.event.ticket.business.result;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class ResultFailure extends ServiceResult {
	private List<BusinessError> errorList = new ArrayList<>();
}
