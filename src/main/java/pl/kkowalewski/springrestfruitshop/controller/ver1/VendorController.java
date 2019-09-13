package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorListDto;
import pl.kkowalewski.springrestfruitshop.service.vendor.VendorService;

@RestController
@RequestMapping(AppConstant.VENDORS_ROOT_PATH)
public class VendorController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final VendorService vendorService;

    /*------------------------ METHODS REGION ------------------------*/
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping({AppConstant.ROOT, AppConstant.SLASH})
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getVendorList() {
        return vendorService.getAllVendors();
    }

    @GetMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public VendorDto getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping({AppConstant.ROOT, AppConstant.SLASH})
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createNewVendor(@RequestBody VendorDto vendorDto) {
        return vendorService.createNewVendor(vendorDto);
    }

    @PutMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public VendorDto updateVendor(@PathVariable Long id,
                                  @RequestBody VendorDto vendorDto) {
        return vendorService.saveVendorByDto(id, vendorDto);
    }

    @PatchMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public VendorDto patchVendor(@PathVariable Long id,
                                 @RequestBody VendorDto vendorDto) {
        return vendorService.saveVendorByDto(id, vendorDto);
    }

    @DeleteMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }
}
