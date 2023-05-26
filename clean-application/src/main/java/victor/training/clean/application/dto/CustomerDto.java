package victor.training.clean.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import victor.training.clean.domain.model.Customer;

import javax.validation.constraints.Size;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Builder
@Value
@AllArgsConstructor
public class CustomerDto { // Dto used to both QUERY and COMMAND use-cases ?
  Long id; // GET (from sequence in DB)

  //@Size(min = 5, message = "{customer-name-too-short}")
  String name; // *

  String email; // *

  Long siteId; // *

  String creationDateStr; // GET (server-side assigned)

  boolean gold; // GET & PUT
  String goldMemberRemovalReason; // GET & PUT if gold=true->false

  String legalEntityCode; // *
  boolean discountedVat; // GET (server-side inferred)

  public CustomerDto(Customer customer) {
    id = customer.getId();
    name = customer.getName();
    email = customer.getEmail();
    siteId = customer.getSite().getId();
    creationDateStr = customer.getCreationDate().format(ofPattern("yyyy-MM-dd"));
    gold = customer.isGoldMember();
    goldMemberRemovalReason = customer.getGoldMemberRemovalReason();
    legalEntityCode = customer.getLegalEntityCode();
    discountedVat = customer.isDiscountedVat();
  }
}
