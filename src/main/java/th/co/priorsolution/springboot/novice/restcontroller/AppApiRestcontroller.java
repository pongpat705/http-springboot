package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.*;
import th.co.priorsolution.springboot.novice.model.NameModel;
import th.co.priorsolution.springboot.novice.model.PersonModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

@RestController
@RequestMapping("/api")
public class AppApiRestcontroller {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hi")
    public ResponseModel<PersonModel> hi(){
        ResponseModel<PersonModel> result = new ResponseModel<>();

        PersonModel personModel = new PersonModel();
        personModel.setEmail("A@B.Com");
        personModel.setName("Fluke");
        personModel.setCid("111111");

        result.setData(personModel);
        result.setStatus(200);
        result.setDescription("ok");

        return result;
    }

    @PostMapping("/hi")
    public ResponseModel<PersonModel> postHi(@RequestBody NameModel model){
            ResponseModel<PersonModel> result = new ResponseModel<>();

            PersonModel personModel = new PersonModel();
            personModel.setEmail("A@B.Com");
            personModel.setName(model.getName());
            personModel.setCid("111111");

            result.setData(personModel);
            result.setStatus(200);
            result.setDescription("ok");

        return result;
    }
}
