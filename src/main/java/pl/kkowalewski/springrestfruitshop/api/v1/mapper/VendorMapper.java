package pl.kkowalewski.springrestfruitshop.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.model.Vendor;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDto vendorDto);
}
