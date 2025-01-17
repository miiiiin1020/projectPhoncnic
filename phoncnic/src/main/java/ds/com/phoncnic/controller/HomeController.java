package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
    
    @GetMapping({"","/"})
    public String home(){
        return "index";
    }


    @GetMapping("/crossroad")
    public String crossRoad() {
        return "crossroad";
    }


    /*
        dyning에서 restaurnat 랑 cafe가는 controller 어떻게 했는지 확인하고
        url어떻게 받으면 좋을지 생각해봐야함 
    */
    @GetMapping("/crossroad/gallery")
    public String crossRoadToGallery() {
        log.info("get gallery.......");
        return "redirect:/gallery";
    }

    // 이걸 한번에 받아서 DyningController에서 변수값 분기해주는 방법은 없나
    // dyning에서 여기서 한번 분기하고 뒤에 따라오는 변수값을 보내는 방법
    @GetMapping("/crossroad/dyning/{choice}")
    public String crossRoadToDyning(@PathVariable("choice") String choice) {
        log.info("get"+choice+".......");
        return "redirect:/dyning/"+choice;
    }

    @GetMapping("/main/companyinfo")
    public String companyinfo() {
        return "/main/companyinfo";
    }

    @GetMapping("/main/help")
    public String help() {
        return "/main/help";
    }     
}
