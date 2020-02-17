package pl.kkowalewski.springrestfruitshop.api.v1.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VendorDto {

    /*------------------------ FIELDS REGION ------------------------*/
    @ApiModelProperty(value = "Vendor Name", required = true)
    private String name;

    @ApiModelProperty(value = "Vendor URL")
    @JsonProperty("vendor_url")
    private String vendorUrl;

    /*------------------------ METHODS REGION ------------------------*/
    public VendorDto() {
    }

    public VendorDto(String name) {
        this.name = name;
    }

    public VendorDto(String name, String vendorUrl) {
        this.name = name;
        this.vendorUrl = vendorUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendorDto vendorDto = (VendorDto) o;

        return new EqualsBuilder()
                .append(name, vendorDto.name)
                .append(vendorUrl, vendorDto.vendorUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(vendorUrl)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("vendorUrl", vendorUrl)
                .toString();
    }
}
