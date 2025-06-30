package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.entity.Item;
import lk.earth.earthuniversity.model.entity.Supplier;
import lk.earth.earthuniversity.model.entity.User;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import lk.earth.earthuniversity.util.RegexProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;


@CrossOrigin
@RestController
@RequestMapping(value = "/regexes")
public class RegexController {

    @GetMapping(path ="/employee", produces = "application/json")
    public ResponseEntity<APISuccessResponse<HashMap<String,HashMap<String,String>>>> employee() {
       HashMap<String,HashMap<String,String>> regexes =  RegexProvider.get(new Employee());
       return APIResponseBuilder.getResponse(regexes, regexes.size());
    }

    @GetMapping(path ="/users", produces = "application/json")
    public ResponseEntity<APISuccessResponse<HashMap<String,HashMap<String,String>>>>  user() {
        HashMap<String,HashMap<String,String>> regexes = RegexProvider.get(new User());
        return APIResponseBuilder.getResponse(regexes, regexes.size());
    }

    @GetMapping(path ="/item", produces = "application/json")
    public ResponseEntity<APISuccessResponse<HashMap<String,HashMap<String,String>>>>  Item() {
        HashMap<String,HashMap<String,String>> regexes =  RegexProvider.get(new Item());
        return APIResponseBuilder.getResponse(regexes, regexes.size());
    }

    @GetMapping(path ="/supplier", produces = "application/json")
    public ResponseEntity<APISuccessResponse<HashMap<String,HashMap<String,String>>>> Supplier() {
        HashMap<String,HashMap<String,String>> regexes = RegexProvider.get(new Supplier());
        return APIResponseBuilder.getResponse(regexes, regexes.size());
    }


}


