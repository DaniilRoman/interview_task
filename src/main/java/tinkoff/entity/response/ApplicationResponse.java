package tinkoff.entity.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinkoff.entity.Application;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {

    @JsonProperty("CONTACT_ID")
    @JsonAlias({ "CONTACT_ID" })
    private Integer contactId;
    @JsonProperty("APPLICATION_ID")
    @JsonAlias({ "APPLICATION_ID" })
    private Integer applicationId;
    @JsonProperty("DT_CREATED")
    @JsonAlias({ "DT_CREATED" })
    private Date created;
    @JsonProperty("PRODUCT_NAME")
    @JsonAlias({ "PRODUCT_NAME" })
    private String productName;

    public ApplicationResponse(Application application, Integer contactId) {
        this.contactId = contactId;
        applicationId = application.getApplicationId();
        created = application.getCreated();
        productName = application.getProductName();
    }

}
