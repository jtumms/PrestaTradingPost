package com.tummsmedia.controllers;

import com.tummsmedia.entities.Image;
import com.tummsmedia.entities.Item;
import com.tummsmedia.entities.User;
import com.tummsmedia.services.ItemRepo;
import com.tummsmedia.services.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by john.tumminelli on 11/25/16.
 */
@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
@RequestMapping(value = "/item-add")
public class AddItemController {
    @Autowired
    ItemRepo items;
    @Autowired
    UserRepo users;

    @RequestMapping(value = "/upload-single")
    public ResponseEntity<Object> addNewItem(@RequestParam ("uploadedFile") MultipartFile uploadedFileRef, HttpSession session,
                                             String itemName,
                                             String itemDescription,
                                             String estValue,
                                             String askingPrice,
                                             String category,
                                             String condition) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            String noValidLogin = "User not logged in";
            return new ResponseEntity<Object>(noValidLogin, HttpStatus.FORBIDDEN);
        }
        File uploadDir = new File("public/images");
        String fileName = uploadedFileRef.getOriginalFilename();
        String path = "public/images/" + fileName;
        byte[] buffer = new byte[1000];
        File outputFile = new File(path);

        FileInputStream reader = null;
        FileOutputStream writer = null;
        int totalBytes = 0;
        try {
            outputFile.createNewFile();


            // Create writer for 'outputFile' to write data read from
            // 'uploadedFileRef'
            writer = new FileOutputStream(outputFile);

            // Iteratively read data from 'uploadedFileRef' and write to
            // 'outputFile';
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer);
                totalBytes += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Image> itemImageSet = new ArrayList<>();
        Image image = new Image(fileName);
        itemImageSet.add(image);
        Item.Category cat = Enum.valueOf(Item.Category.class, category);
        Item.Condition cond = Enum.valueOf(Item.Condition.class, condition);
        Item item = new Item(itemName, itemDescription, cat, Long.parseLong(estValue), Long.parseLong(askingPrice), cond, itemImageSet, user);
        items.save(item);
        String success =  "Item Data and File uploaded successfully! Total Bytes Read="+totalBytes;
        return new ResponseEntity<Object>(success, HttpStatus.OK);

    }
    @RequestMapping(value = "/upload")
    public ResponseEntity<Object> addNewItem(@RequestParam ("uploadedFiles") MultipartFile[] uploadedFilesRef, HttpSession session,
                                             String itemName,
                                             String itemDescription,
                                             String estValue,
                                             String askingPrice,
                                             String category,
                                             String condition) throws IOException{
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            String noValidLogin = "User not logged in";
            return new ResponseEntity<Object>(noValidLogin, HttpStatus.FORBIDDEN);
        }
        List<Image> itemImageSet = new ArrayList<>();
        int imgCount = 0;
        for(int i =0 ;i < uploadedFilesRef.length; i++) {
            if (uploadedFilesRef[i] != null && uploadedFilesRef[i].getSize() > 0){
                String fileName = null;
                fileName = uploadedFilesRef[i].getOriginalFilename();
                String path = "public/images/" + fileName;
                byte[] buffer = uploadedFilesRef[i].getBytes();
                File outputFile = new File(path);
                BufferedOutputStream buffstream = new BufferedOutputStream(
                        new FileOutputStream(outputFile));
                buffstream.write(buffer);
                buffstream.close();

                Image image = new Image(fileName);
                itemImageSet.add(image);
                imgCount += 1;
                }
            }
        Item.Category cat = Enum.valueOf(Item.Category.class, category);
        Item.Condition cond = Enum.valueOf(Item.Condition.class, condition);
        Item item = new Item(itemName, itemDescription, cat, Long.parseLong(estValue), Long.parseLong(askingPrice), cond, itemImageSet, user);
        items.save(item);
        String success = String.format("Item Data and %s File(s) uploaded successfully!", imgCount);
        return new ResponseEntity<Object>(success, HttpStatus.OK);
    }
}
