package com.wexinc.wexifcsmicroservice.service;

import com.wexinc.wexifcsmicroservice.model.InterfaceFiles;
import com.wexinc.wexifcsmicroservice.model.OutIntRequest;
import com.wexinc.wexifcsmicroservice.model.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class IFCSEmbossService {

    @Autowired
    RestTemplate template;

    public void createEmbossFile(OutIntRequest request) throws IOException {

        //Here the file gets created and returns a notification to gateway
        BufferedWriter writer = new BufferedWriter(new FileWriter("C://Ram/myrepo/ifcsfiles/" + request.getInterfaceID() + ".txt"));
        writer.write("This is test file created by WEXIFCSMicroservice"+request.getUniqueIdentifier());
        writer.close();
        ProcessResponse response = new ProcessResponse();
        List<InterfaceFiles> fileList = new ArrayList<InterfaceFiles>();
        InterfaceFiles files =  new InterfaceFiles();
        files.setFileName(request.getInterfaceID() + ".txt");
        files.setFilePath("C://Ram/myrepo/ifcsfiles/" + request.getInterfaceID() + ".txt");
        fileList.add(files);
        response.setInterfaceID(request.getInterfaceID());
        response.setClientId(request.getClientId());
        response.setUniqueIdentifier(request.getUniqueIdentifier());
        response.setInputFiles(fileList);
        response.setResult("Success");
        // call the rest end point straight
        template.postForObject("http://wex-gateway-microservice/wexgateway/callback", response,  String.class) ;


    }

}
