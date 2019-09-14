package pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class VendorListDto {

    /*------------------------ FIELDS REGION ------------------------*/
    @ApiModelProperty(value = "List of VendorDto")
    private List<VendorDto> vendorDtoList;

    /*------------------------ METHODS REGION ------------------------*/
    public VendorListDto() {
    }

    public VendorListDto(List<VendorDto> vendorDtoList) {
        this.vendorDtoList = vendorDtoList;
    }

    public List<VendorDto> getVendorDtoList() {
        return vendorDtoList;
    }

    public void setVendorDtoList(List<VendorDto> vendorDtoList) {
        this.vendorDtoList = vendorDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendorListDto that = (VendorListDto) o;

        return new EqualsBuilder()
                .append(vendorDtoList, that.vendorDtoList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(vendorDtoList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("vendorDtoList", vendorDtoList)
                .toString();
    }
}
