package pl.kkowalewski.springrestfruitshop.service.vendor;

import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorListDto;

public interface VendorService {

    VendorDto getVendorById(Long id);

    VendorListDto getAllVendors();

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto saveVendorByDto(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);
}
