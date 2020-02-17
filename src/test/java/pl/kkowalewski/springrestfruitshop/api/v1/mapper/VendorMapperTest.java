package pl.kkowalewski.springrestfruitshop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.model.Vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VendorMapperTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String NAME = "ABC";
    private static final String URL = "XYZ";

    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    /*------------------------ METHODS REGION ------------------------*/

    @Test
    public void vendorToVendorDtoTest() {
        VendorDto vendorDto = vendorMapper.vendorToVendorDto(new Vendor(ID, NAME));

        assertEquals(NAME, vendorDto.getName());
    }

    @Test
    public void vendorToVendorDtoNullTest() {
        VendorDto vendorDto = vendorMapper.vendorToVendorDto(null);

        assertNull(vendorDto);
    }

    @Test
    public void vendorDtoToVendorTest() {
        Vendor vendor = vendorMapper.vendorDtoToVendor(new VendorDto(NAME, URL));

        assertEquals(NAME, vendor.getName());
    }

    @Test
    public void vendorDtoToVendorNullTest() {
        Vendor vendor = vendorMapper.vendorDtoToVendor(null);

        assertNull(vendor);
    }
}
    