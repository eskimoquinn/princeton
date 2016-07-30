package org.watchablesdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tgreen on 7/27/16.
 */
@RequestMapping(value = "/")
@Controller
public class IndexApi {


    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public String index() {
        return "Welcome to the world of watchables";
    }
}