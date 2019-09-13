package pl.kkowalewski.springrestfruitshop.service.vendor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.VendorMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorListDto;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;
import pl.kkowalewski.springrestfruitshop.model.Vendor;
import pl.kkowalewski.springrestfruitshop.repository.VendorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class VendorServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID_ONE = 1L;
    private static final Long ID_TWO = 2L;
    private static final String NAME_ONE = "ABC";
    private static final String NAME_TWO = "CDE";

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    private Vendor vendorOne;
    private Vendor vendorTwo;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        vendorOne = new Vendor(ID_ONE, NAME_ONE);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    private List<Vendor> prepareVendorList(Vendor... vendors) {
        List<Vendor> vendorList = new ArrayList<>();

        for (Vendor it : vendors) {
            vendorList.add(it);
        }

        return vendorList;
    }

    @Test
    public void getVendorByIdTest() {
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendorOne));
        VendorDto vendorDto = vendorService.getVendorById(ID_ONE);
        then(vendorRepository).should().findById(anyLong());

        assertThat(vendorDto.getName(), is(NAME_ONE));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFoundTest() {
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());
        vendorService.getVendorById(ID_ONE);
        then(vendorRepository).should().findById(anyLong());
    }

    @Test
    public void getAllVendorsTest() {
        vendorTwo = new Vendor(ID_TWO, NAME_TWO);

        given(vendorRepository.findAll()).willReturn(prepareVendorList(vendorOne, vendorTwo));
        VendorListDto vendorListDto = vendorService.getAllVendors();
        then(vendorRepository).should().findAll();

        assertThat(vendorListDto.getVendorDtoList().size(), is(2));
    }

    @Test
    public void createNewVendorTest() {
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendorOne);
        VendorDto vendorDto = vendorService.createNewVendor(new VendorDto(NAME_TWO));
        then(vendorRepository).should().save(any(Vendor.class));

        assertThat(vendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void saveVendorByDtoTest() {
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendorOne);
        VendorDto vendorDto = vendorService.saveVendorByDto(ID_ONE, new VendorDto(NAME_ONE));
        then(vendorRepository).should().save(any(Vendor.class));

        assertThat(vendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void patchVendorTest() {
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendorOne));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendorOne);

        VendorDto vendorDto = vendorService.patchVendor(ID_ONE, new VendorDto(NAME_ONE));

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should().findById(anyLong());

        assertThat(vendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorByIdTest() {
        vendorService.deleteVendorById(ID_ONE);

        then(vendorRepository).should().deleteById(anyLong());
    }
}
    