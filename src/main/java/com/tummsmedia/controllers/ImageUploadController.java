package com.tummsmedia.controllers;

import com.tummsmedia.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by john.tumminelli on 11/21/16.
 */
@Controller
public class ImageUploadController {
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {

        File dir = new File("public/files");
        dir.mkdirs();
        StringBuilder sb = new StringBuilder();

        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        Image itemImage = new Image(f.getName(), file.getOriginalFilename());
        files.save(anonFile);


    }

}
