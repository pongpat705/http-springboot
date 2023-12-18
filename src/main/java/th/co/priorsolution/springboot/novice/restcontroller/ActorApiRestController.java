package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.priorsolution.springboot.novice.model.ActorResponseModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.service.ActorService;

@RestController
@RequestMapping("/api")
public class ActorApiRestController {

    private ActorService actorService;

    public ActorApiRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actor/{number}")
    public ResponseModel<ActorResponseModel> getActorByNumber(@PathVariable(name = "number") String number){
        return this.actorService.getActorById(number);
    }

}
