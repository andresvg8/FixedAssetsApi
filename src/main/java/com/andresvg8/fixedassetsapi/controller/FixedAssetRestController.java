package com.andresvg8.fixedassetsapi.controller;

import com.andresvg8.fixedassetsapi.entity.City;
import com.andresvg8.fixedassetsapi.entity.CompanyArea;
import com.andresvg8.fixedassetsapi.entity.Employee;
import com.andresvg8.fixedassetsapi.entity.FixedAsset;
import com.andresvg8.fixedassetsapi.service.CityService;
import com.andresvg8.fixedassetsapi.service.CompanyAreaService;
import com.andresvg8.fixedassetsapi.service.EmployeeService;
import com.andresvg8.fixedassetsapi.service.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class FixedAssetRestController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CompanyAreaService companyAreaService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FixedAssetService fixedAssetService;

    @GetMapping("/type/{type}")
    @ResponseBody
    public ResponseEntity<?> getFixedAssetByType(
            @PathVariable String type
    ){
        Optional<List<FixedAsset>> optionalFixedAsset = fixedAssetService.findByType(type);
        if(!optionalFixedAsset.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Cannot recover any data from the database.");
        }
        if(optionalFixedAsset.get().size()<1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are no data with the given asset type.");
        }
        return ResponseEntity.ok(optionalFixedAsset.get());
    }

    @GetMapping("/purchase_date/{purchaseDate}")
    @ResponseBody
    public ResponseEntity<?> getFixedAssetByPurchaseDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate purchaseDate
    ){
        Optional<List<FixedAsset>> optionalFixedAsset = fixedAssetService.findByPurchaseDate(purchaseDate);
        if(!optionalFixedAsset.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Cannot retrieve data from the database.");
        }
        if(optionalFixedAsset.get().size()<1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are no data with the given purchase date.");
        }
        return ResponseEntity.ok(optionalFixedAsset.get());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getFixedAssetBySerial(
            @PathVariable String serial
    ){
        Optional<List<FixedAsset>> optionalFixedAsset = fixedAssetService.findBySerial(serial);
        if(!optionalFixedAsset.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
        if(optionalFixedAsset.get().size()<1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are no data with the given serial.");
        }
        return ResponseEntity.ok(optionalFixedAsset.get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFixedAsset(
            @RequestBody FixedAsset newFixedAsset
    ){
        if(newFixedAsset.getId()!=null){
            Optional<FixedAsset> optionalFixedAsset = fixedAssetService.findById(newFixedAsset.getId());
            if(optionalFixedAsset.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The given asset has an id number that already exists in the database.");
            }
            newFixedAsset.setId(null);
        }
        newFixedAsset = fixedAssetService.save(newFixedAsset);
        return ResponseEntity.status(HttpStatus.OK).body(newFixedAsset);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFixedAsset(
            @NotNull @RequestBody FixedAsset fixedAsset
    ){
        if(fixedAsset.getId()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The given asset should have an id number.");
        }
        Optional<FixedAsset> optionalFixedAsset = fixedAssetService.findById( fixedAsset.getId() );
        if(!optionalFixedAsset.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The asset with the given id number does not exist in the database.");
        }
        fixedAsset = fixedAssetService.save(fixedAsset);
        return ResponseEntity.status(HttpStatus.OK).body(fixedAsset);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllAssets(){
        this.setFakeValuesIfPossible();
        Optional<List<FixedAsset>> optionalAssets = fixedAssetService.findAll();
        if(!optionalAssets.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Can not recover data from the database.");
        }
        if(optionalAssets.get().size()<1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no assets data stored in the database.");
        }
        return ResponseEntity.ok(optionalAssets.get());
    }

    private boolean areFakeValuesSetted = false;
    @GetMapping("/setdefaults")
    public ResponseEntity<?> setFakeValuesIfPossible(){
        if( !(this.areFakeValuesSetted) ){
            try{
                String[] cityNames = {"Bogotá", "Cali", "Villavicencio"};
                for(String cityName : cityNames){
                    City city = new City();
                    city.setName(cityName);
                    city = cityService.save(city);
                }
                List<City> cities = cityService.findAll();
                List<String> areaNames = Arrays.asList("Management", "IT");
                for(City city : cities){
                    for(String areaName : areaNames){
                        CompanyArea companyArea = new CompanyArea();
                        companyArea.setName(areaName);
                        companyArea = companyAreaService.save(companyArea);
                        companyArea.getCities().add(city);
                        companyArea = companyAreaService.save(companyArea);
                    }
                }
                List<CompanyArea> companyAreas = companyAreaService.findAll();
                System.out.println("Company areas: ");
                for(CompanyArea area : companyAreas){
                    System.out.println(""+area.toString());
                }

                String[] employeeNames = {"Andrés Villalba Gaviria", "Employee A", "Employee B", "Employee C", "Employee D", "Employee E", "Employee F", "Employee G", "Employee H", "Employee I"};
                for(String employeeName : employeeNames){
                    Employee employee = new Employee();
                    employee.setName(employeeName);
                    employee = employeeService.save(employee);
                }
                int assetsQuantity = 10;
                String[] assetNames =               {"Asus TUF F15",        "Asus Vivobook",    "Lenovo Thinkpad",  "Workstation",          "Workstation",          "Workstation",          "Workstation",          "Blue bag",                 "Brown bag",                "Gray bag"};
                String[] assetDescriptions =        {"Laptop computer",     "Laptop computer",  "Laptop computer",  "Desktop and chair",    "Desktop and chair",    "Desktop and chair",    "Desktop and chair",    "Bag to carry computer",    "Bag to carry computer",    "Bag to carry computer"};
                String[] assetTypes =               {"Computer",            "Computer",         "Computer",         "Desktop",              "Desktop",              "Desktop",              "Desktop",              "Bag",                      "Bag",                      "Bag"};
                String[] assetSerials =             {"12345",               "67890",            "11223",            "34455",                "66778",                "89900",                "11122",                "23334",                    "44555",                    "66677"};
                String[] assetInventoryNumbers =    {"C01",                 "C02",              "C03",              "D01",                  "D02",                  "D03",                  "D04",                  "B01",                      "B02",                      "B03"};
                Double[] assetWeights = new Double[assetsQuantity];
                Double[] assetHeights = new Double[assetsQuantity];
                Double[] assetWidths = new Double[assetsQuantity];
                Double[] assetLongitudes = new Double[assetsQuantity];
                Double[] assetPurchasePrices = new Double[assetsQuantity];
                LocalDate[] assetPurchaseDates = new LocalDate[assetsQuantity];
                for(int x=0; x<assetsQuantity; x++){
                    assetWeights[x] = Math.random()*4;
                    assetHeights[x] = Math.random()*6;
                    assetWidths[x] = Math.random()*6;
                    assetLongitudes[x] = Math.random()*7;
                    assetPurchasePrices[x] = Math.random()*4000000;
                    assetPurchaseDates[x] = LocalDate.of(2019+(int)(Math.random()*3), 1+(int)(Math.random()*12), 1+(int)(Math.random()*28));
                }
                for(int x=0; x<assetsQuantity; x++){
                    FixedAsset asset = new FixedAsset();
                    asset.setName(assetNames[x]);
                    asset.setDescription(assetDescriptions[x]);
                    asset.setType(assetTypes[x]);
                    asset.setSerial(assetSerials[x]);
                    asset.setInventoryNumber(assetInventoryNumbers[x]);
                    asset.setWeight(assetWeights[x]);
                    asset.setHeight(assetHeights[x]);
                    asset.setWidth(assetWidths[x]);
                    asset.setLongitude(assetLongitudes[x]);
                    asset.setPurchasePrice(assetPurchasePrices[x]);
                    asset.setPurchaseDate(assetPurchaseDates[x]);
                    fixedAssetService.save(asset);
                }
            }
            catch (Exception exception){
                return ResponseEntity.internalServerError().body("Internal server error.");
            }
        }
        this.areFakeValuesSetted = true;
        return ResponseEntity.ok("Default values were setted into the database.");
    }
}
