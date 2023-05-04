package com.wexinc.wexifcsmicroservice.controller;

import com.wexinc.wexifcsmicroservice.model.OutIntRequest;
import com.wexinc.wexifcsmicroservice.service.IFCSEmbossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/embossCard")
public class EmbossCardController {

    @Autowired
    IFCSEmbossService service;

    @PostMapping(path = "/invokeEmbossCard")
    public String invokeEmbossCards(@RequestBody OutIntRequest process) throws Exception {
        System.out.println("In WEXIFCSService - EmbossCardController"+process.getInterfaceID()+" Unique ID"+process.getUniqueIdentifier());
        //Writes emboss card file
        service.createEmbossFile(process);

        return "Success";
    }

}
