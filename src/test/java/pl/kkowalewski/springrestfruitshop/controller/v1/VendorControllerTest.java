package pl.kkowalewski.springrestfruitshop.controller.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.api.v1.model.vendor.VendorListDto;
import pl.kkowalewski.springrestfruitshop.service.vendor.VendorService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.VENDORS_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.controller.v1.AbstractRestControllerTest.asJsonString;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String NAME_ONE = "ABC";
    private static final String NAME_TWO = "CDE";
    private static final String URL_ONE = VENDORS_ROOT_PATH + SLASH + 1;
    private static final String URL_TWO = VENDORS_ROOT_PATH + SLASH + 2;

    @MockBean
    private VendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    private VendorDto vendorDtoOne;
    private VendorDto vendorDtoTwo;

    /*------------------------ METHODS REGION ------------------------*/
    @BeforeEach
    public void setUp() {
        vendorDtoOne = new VendorDto(NAME_ONE, URL_ONE);
        vendorDtoTwo = new VendorDto(NAME_TWO, URL_TWO);
    }

    private VendorListDto prepareVendorDtoList(VendorDto... vendorDtos) {
        List<VendorDto> vendorDtoList = new ArrayList<>();

        for (VendorDto it : vendorDtos) {
            vendorDtoList.add(it);
        }

        return new VendorListDto(vendorDtoList);
    }

    @Test
    public void getVendorListTest() throws Exception {
        given(vendorService.getAllVendors())
                .willReturn(prepareVendorDtoList(vendorDtoOne, vendorDtoTwo));

        mockMvc.perform(get(VENDORS_ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorDtoList", hasSize(2)));
    }

    @Test
    public void getVendorByIdTest() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDtoOne);

        mockMvc.perform(get(URL_ONE)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDtoOne.getName())));
    }

    @Test
    public void createNewVendorTest() throws Exception {
        given(vendorService.createNewVendor(vendorDtoOne)).willReturn(vendorDtoOne);

        mockMvc.perform(post(VENDORS_ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDtoOne))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDtoOne.getName())));
    }

    @Test
    public void updateVendorTest() throws Exception {
        given(vendorService.saveVendorByDto(anyLong(), any(VendorDto.class)))
                .willReturn(vendorDtoOne);

        mockMvc.perform(put(URL_ONE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDtoOne))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDtoOne.getName())));
    }

    @Test
    public void patchVendorTest() throws Exception {
        given(vendorService.saveVendorByDto(anyLong(), any(VendorDto.class)))
                .willReturn(vendorDtoOne);

        mockMvc.perform(patch(URL_ONE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDtoOne))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDtoOne.getName())));
    }

    @Test
    public void deleteVendorTest() throws Exception {
        mockMvc.perform(delete(URL_ONE))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }
}
    