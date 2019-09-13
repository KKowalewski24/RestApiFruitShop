package pl.kkowalewski.springrestfruitshop.service.vendor;

import org.springframework.stereotype.Service;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.VendorMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.vendor.VendorListDto;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;
import pl.kkowalewski.springrestfruitshop.model.Vendor;
import pl.kkowalewski.springrestfruitshop.repository.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.VENDORS_ROOT_PATH;

@Service
public class VendorServiceImpl implements VendorService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    private VendorDto prepareVendorDto(Vendor vendor) {
        Vendor vendorSave = vendorRepository.save(vendor);
        VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendorSave);
        vendorDto.setVendorUrl(VENDORS_ROOT_PATH + SLASH + vendorSave.getId());

        return vendorDto;
    }

    @Override
    public VendorDto getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDto)
                .map(vendorDto -> {
                    vendorDto.setVendorUrl(VENDORS_ROOT_PATH + SLASH + id);
                    return vendorDto;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDto getAllVendors() {
        List<VendorDto> vendorDtoList = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                    vendorDto.setVendorUrl(VENDORS_ROOT_PATH + SLASH + vendor.getId());
                    return vendorDto;
                }).collect(Collectors.toList());

        return new VendorListDto(vendorDtoList);
    }

    @Override
    public VendorDto createNewVendor(VendorDto vendorDto) {
        return prepareVendorDto(vendorMapper.vendorDtoToVendor(vendorDto));
    }

    @Override
    public VendorDto saveVendorByDto(Long id, VendorDto vendorDto) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);
        vendor.setId(id);

        return prepareVendorDto(vendor);
    }

    @Override
    public VendorDto patchVendor(Long id, VendorDto vendorDto) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if (vendorDto.getName() != null) {
                        vendor.setName(vendorDto.getName());
                    }
                    return prepareVendorDto(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
