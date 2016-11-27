package com.tummsmedia.entities;

import com.sun.javafx.fxml.builder.URLBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.tummsmedia.controllers.ItemController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.util.*;
import java.util.UUID;

/**
 * Created by john.tumminelli on 11/26/16.
 */
public class Message {

    public String owner;
    public int renterId;
    public String body;
    public String subject;
    public String authKey;
    private HashMap<String, String> dataMap;

    public Message() {
    }

    public Message(String owner, int renterId, String body, String subject, String authKey, HashMap<String, String> dataMap) {
        this.owner = owner;
        this.renterId = renterId;
        this.body = body;
        this.subject = subject;
        this.authKey = authKey;
        this.dataMap = dataMap;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }

    public static ClientResponse sendOwnerMessage(Item itemBorrowed) {

        Message message = new Message();
        String generatedKey = UUID.randomUUID().toString();
        message.authKey = generatedKey;
        message.setOwner(ItemController.emailDataMap.get("owner"));
        message.setRenterId( Integer.valueOf(ItemController.emailDataMap.get("borrowerId")));
        String subjectText = String.format("You have a rental request for your one of your items. Transaction ID: %s", ItemController.emailDataMap.get("transactionId"));
        message.setSubject(subjectText);

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-e40dbe458be098bc00352bae39fd72ba"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org/" +
                        "messages");
        FormDataMultiPart form = new FormDataMultiPart();
        form.field("from", "Mailgun Sandbox <postmaster@sandbox24e2ae74809546f08a2ce2168f7ba9e8.mailgun.org>");
        form.field("to", message.getOwner());
//        form.field("text", "Test text body");
        form.field("subject", subjectText);
        String body = getHtmlMessageBody();
        form.field("html", body);
        String file_separator = System.getProperty("file.separator");
//        File txtFile = new File("." + file_separator +
//                "files" + file_separator + "test.txt");
//        form.bodyPart(new FileDataBodyPart("attachment", txtFile,
//                MediaType.TEXT_PLAIN_TYPE));
        ArrayList<Object> imageArrayList = new ArrayList<>();
        Iterator iterator = itemBorrowed.getImages().iterator();
        while (iterator.hasNext()){
            imageArrayList.add(iterator.next());
        }Image image = (Image) imageArrayList.get(0);

        File jpgFile = new File("public" + file_separator + "images" + file_separator + image.getImageFileName());
        form.bodyPart(new FileDataBodyPart("attachment",jpgFile,
                MediaType.APPLICATION_OCTET_STREAM_TYPE));
        return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, form);
    }

    public static String getHtmlMessageBody(){
        String htmlMesssageBody = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "\t<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "\t\t<meta name=\"format-detection\" content=\"telephone=no\" /> <!-- disable auto telephone linking in iOS -->\n" +
                "\t\t<title>Respmail is a response HTML email designed to work on all major email platforms and smartphones</title>\n" +
                "\t\t<style type=\"text/css\">\n" +
                "\t\t\t/* RESET STYLES */\n" +
                "\t\t\thtml { background-color:#E1E1E1; margin:0; padding:0; }\n" +
                "\t\t\tbody, #bodyTable, #bodyCell, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;font-family:Helvetica, Arial, \"Lucida Grande\", sans-serif;}\n" +
                "\t\t\ttable{border-collapse:collapse;}\n" +
                "\t\t\ttable[id=bodyTable] {width:100%!important;margin:auto;max-width:500px!important;color:#7A7A7A;font-weight:normal;}\n" +
                "\t\t\timg, a img{border:0; outline:none; text-decoration:none;height:auto; line-height:100%;}\n" +
                "\t\t\ta {text-decoration:none !important;border-bottom: 1px solid;}\n" +
                "\t\t\th1, h2, h3, h4, h5, h6{color:#5F5F5F; font-weight:normal; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left; letter-spacing:normal;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;}\n" +
                "\n" +
                "\t\t\t/* CLIENT-SPECIFIC STYLES */\n" +
                "\t\t\t.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\n" +
                "\t\t\t.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\n" +
                "\t\t\ttable, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\n" +
                "\t\t\t#outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\n" +
                "\t\t\timg{-ms-interpolation-mode: bicubic;display:block;outline:none; text-decoration:none;} /* Force IE to smoothly render resized images. */\n" +
                "\t\t\tbody, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%; font-weight:normal!important;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\n" +
                "\t\t\t.ExternalClass td[class=\"ecxflexibleContainerBox\"] h3 {padding-top: 10px !important;} /* Force hotmail to push 2-grid sub headers down */\n" +
                "\n" +
                "\t\t\t/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */\n" +
                "\n" +
                "\t\t\t/* ========== Page Styles ========== */\n" +
                "\t\t\th1{display:block;font-size:26px;font-style:normal;font-weight:normal;line-height:100%;}\n" +
                "\t\t\th2{display:block;font-size:20px;font-style:normal;font-weight:normal;line-height:120%;}\n" +
                "\t\t\th3{display:block;font-size:17px;font-style:normal;font-weight:normal;line-height:110%;}\n" +
                "\t\t\th4{display:block;font-size:18px;font-style:italic;font-weight:normal;line-height:100%;}\n" +
                "\t\t\t.flexibleImage{height:auto;}\n" +
                "\t\t\t.linkRemoveBorder{border-bottom:0 !important;}\n" +
                "\t\t\ttable[class=flexibleContainerCellDivider] {padding-bottom:0 !important;padding-top:0 !important;}\n" +
                "\n" +
                "\t\t\tbody, #bodyTable{background-color:#E1E1E1;}\n" +
                "\t\t\t#emailHeader{background-color:#E1E1E1;}\n" +
                "\t\t\t#emailBody{background-color:#FFFFFF;}\n" +
                "\t\t\t#emailFooter{background-color:#E1E1E1;}\n" +
                "\t\t\t.nestedContainer{background-color:#F8F8F8; border:1px solid #CCCCCC;}\n" +
                "\t\t\t.emailButton{background-color:#205478; border-collapse:separate;}\n" +
                "\t\t\t.buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\n" +
                "\t\t\t.buttonContent a{color:#FFFFFF; display:block; text-decoration:none!important; border:0!important;}\n" +
                "\t\t\t.emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\n" +
                "\t\t\t.emailCalendarMonth{background-color:#205478; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\n" +
                "\t\t\t.emailCalendarDay{color:#205478; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\n" +
                "\t\t\t.imageContentText {margin-top: 10px;line-height:0;}\n" +
                "\t\t\t.imageContentText a {line-height:0;}\n" +
                "\t\t\t#invisibleIntroduction {display:none !important;} /* Removing the introduction text from the view */\n" +
                "\n" +
                "\t\t\t/*FRAMEWORK HACKS & OVERRIDES */\n" +
                "\t\t\tspan[class=ios-color-hack] a {color:#275100!important;text-decoration:none!important;} /* Remove all link colors in IOS (below are duplicates based on the color preference) */\n" +
                "\t\t\tspan[class=ios-color-hack2] a {color:#205478!important;text-decoration:none!important;}\n" +
                "\t\t\tspan[class=ios-color-hack3] a {color:#8B8B8B!important;text-decoration:none!important;}\n" +
                "\t\t\t/* A nice and clean way to target phone numbers you want clickable and avoid a mobile phone from linking other numbers that look like, but are not phone numbers.  Use these two blocks of code to \"unstyle\" any numbers that may be linked.  The second block gives you a class to apply with a span tag to the numbers you would like linked and styled.\n" +
                "\t\t\tInspired by Campaign Monitor's article on using phone numbers in email: http://www.campaignmonitor.com/blog/post/3571/using-phone-numbers-in-html-email/.\n" +
                "\t\t\t*/\n" +
                "\t\t\t.a[href^=\"tel\"], a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:none!important;cursor:default!important;}\n" +
                "\t\t\t.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {text-decoration:none!important;color:#606060!important;pointer-events:auto!important;cursor:default!important;}\n" +
                "\n" +
                "\n" +
                "\t\t\t/* MOBILE STYLES */\n" +
                "\t\t\t@media only screen and (max-width: 480px){\n" +
                "\t\t\t\t/*////// CLIENT-SPECIFIC STYLES //////*/\n" +
                "\t\t\t\tbody{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\n" +
                "\n" +
                "\t\t\t\t/* FRAMEWORK STYLES */\n" +
                "\t\t\t\t/*\n" +
                "\t\t\t\tCSS selectors are written in attribute\n" +
                "\t\t\t\tselector format to prevent Yahoo Mail\n" +
                "\t\t\t\tfrom rendering media query styles on\n" +
                "\t\t\t\tdesktop.\n" +
                "\t\t\t\t*/\n" +
                "\t\t\t\t/*td[class=\"textContent\"], td[class=\"flexibleContainerCell\"] { width: 100%; padding-left: 10px !important; padding-right: 10px !important; }*/\n" +
                "\t\t\t\ttable[id=\"emailHeader\"],\n" +
                "\t\t\t\ttable[id=\"emailBody\"],\n" +
                "\t\t\t\ttable[id=\"emailFooter\"],\n" +
                "\t\t\t\ttable[class=\"flexibleContainer\"],\n" +
                "\t\t\t\ttd[class=\"flexibleContainerCell\"] {width:100% !important;}\n" +
                "\t\t\t\ttd[class=\"flexibleContainerBox\"], td[class=\"flexibleContainerBox\"] table {display: block;width: 100%;text-align: left;}\n" +
                "\t\t\t\t/*\n" +
                "\t\t\t\tThe following style rule makes any\n" +
                "\t\t\t\timage classed with 'flexibleImage'\n" +
                "\t\t\t\tfluid when the query activates.\n" +
                "\t\t\t\tMake sure you add an inline max-width\n" +
                "\t\t\t\tto those images to prevent them\n" +
                "\t\t\t\tfrom blowing out.\n" +
                "\t\t\t\t*/\n" +
                "\t\t\t\ttd[class=\"imageContent\"] img {height:auto !important; width:100% !important; max-width:100% !important; }\n" +
                "\t\t\t\timg[class=\"flexibleImage\"]{height:auto !important; width:100% !important;max-width:100% !important;}\n" +
                "\t\t\t\timg[class=\"flexibleImageSmall\"]{height:auto !important; width:auto !important;}\n" +
                "\n" +
                "\n" +
                "\t\t\t\t/*\n" +
                "\t\t\t\tCreate top space for every second element in a block\n" +
                "\t\t\t\t*/\n" +
                "\t\t\t\ttable[class=\"flexibleContainerBoxNext\"]{padding-top: 10px !important;}\n" +
                "\n" +
                "\t\t\t\t/*\n" +
                "\t\t\t\tMake buttons in the email span the\n" +
                "\t\t\t\tfull width of their container, allowing\n" +
                "\t\t\t\tfor left- or right-handed ease of use.\n" +
                "\t\t\t\t*/\n" +
                "\t\t\t\ttable[class=\"emailButton\"]{width:100% !important;}\n" +
                "\t\t\t\ttd[class=\"buttonContent\"]{padding:0 !important;}\n" +
                "\t\t\t\ttd[class=\"buttonContent\"] a{padding:15px !important;}\n" +
                "\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t/*  CONDITIONS FOR ANDROID DEVICES ONLY\n" +
                "\t\t\t*   http://developer.android.com/guide/webapps/targeting.html\n" +
                "\t\t\t*   http://pugetworks.com/2011/04/css-media-queries-for-targeting-different-mobile-devices/ ;\n" +
                "\t\t\t=====================================================*/\n" +
                "\n" +
                "\t\t\t@media only screen and (-webkit-device-pixel-ratio:.75){\n" +
                "\t\t\t\t/* Put CSS for low density (ldpi) Android layouts in here */\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t@media only screen and (-webkit-device-pixel-ratio:1){\n" +
                "\t\t\t\t/* Put CSS for medium density (mdpi) Android layouts in here */\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t@media only screen and (-webkit-device-pixel-ratio:1.5){\n" +
                "\t\t\t\t/* Put CSS for high density (hdpi) Android layouts in here */\n" +
                "\t\t\t}\n" +
                "\t\t\t/* end Android targeting */\n" +
                "\n" +
                "\t\t\t/* CONDITIONS FOR IOS DEVICES ONLY\n" +
                "\t\t\t=====================================================*/\n" +
                "\t\t\t@media only screen and (min-device-width : 320px) and (max-device-width:568px) {\n" +
                "\n" +
                "\t\t\t}\n" +
                "\t\t\t/* end IOS targeting */\n" +
                "\t\t</style>\n" +
                "\t\t<!--\n" +
                "\t\t\tOutlook Conditional CSS\n" +
                "\n" +
                "\t\t\tThese two style blocks target Outlook 2007 & 2010 specifically, forcing\n" +
                "\t\t\tcolumns into a single vertical stack as on mobile clients. This is\n" +
                "\t\t\tprimarily done to avoid the 'page break bug' and is optional.\n" +
                "\n" +
                "\t\t\tMore information here:\n" +
                "\t\t\thttp://templates.mailchimp.com/development/css/outlook-conditional-css\n" +
                "\t\t-->\n" +
                "\t\t<!--[if mso 12]>\n" +
                "\t\t\t<style type=\"text/css\">\n" +
                "\t\t\t\t.flexibleContainer{display:block !important; width:100% !important;}\n" +
                "\t\t\t</style>\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<!--[if mso 14]>\n" +
                "\t\t\t<style type=\"text/css\">\n" +
                "\t\t\t\t.flexibleContainer{display:block !important; width:100% !important;}\n" +
                "\t\t\t</style>\n" +
                "\t\t<![endif]-->\n" +
                "\t</head>\n" +
                "\t<body bgcolor=\"#E1E1E1\" leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\">\n" +
                "\n" +
                "\t\t<!-- CENTER THE EMAIL // -->\n" +
                "\t\t<!--\n" +
                "\t\t1.  The center tag should normally put all the\n" +
                "\t\t\tcontent in the middle of the email page.\n" +
                "\t\t\tI added \"table-layout: fixed;\" style to force\n" +
                "\t\t\tyahoomail which by default put the content left.\n" +
                "\n" +
                "\t\t2.  For hotmail and yahoomail, the contents of\n" +
                "\t\t\tthe email starts from this center, so we try to\n" +
                "\t\t\tapply necessary styling e.g. background-color.\n" +
                "\t\t-->\n" +
                "\t\t<center style=\"background-color:#E1E1E1;\">\n" +
                "\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\" style=\"table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
                "\n" +
                "\t\t\t\t\t\t<!-- EMAIL HEADER // -->\n" +
                "\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\tThe table \"emailBody\" is the email's container.\n" +
                "\t\t\t\t\t\t\tIts width can be set to 100% for a color band\n" +
                "\t\t\t\t\t\t\tthat spans the width of the page.\n" +
                "\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t<table bgcolor=\"#E1E1E1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailHeader\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- HEADER ROW // -->\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe \"invisibleIntroduction\" is the text used for short preview\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tof the email before the user opens it (50 characters max). Sometimes,\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tyou do not want to show this message depending on your design but this\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\ttext is highly recommended.\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYou do not have to worry if it is hidden, the next <td> will automatically\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcenter and apply to the width 100% and also shrink to 50% if the first <td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tis visible.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"middle\" id=\"invisibleIntroduction\" class=\"flexibleContainerBox\" style=\"display:none !important; mso-hide:all;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe introduction of your message preview goes here. Try to make it short.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:100%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tIf you can't see this message, <a href=\"#\" target=\"_blank\" style=\"text-decoration:none;border-bottom:1px solid #828282;color:#828282;\"><span style=\"color:#828282;\">view&nbsp;it&nbsp;in&nbsp;your&nbsp;browser</span></a>.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // END -->\n" +
                "\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t<!-- // END -->\n" +
                "\n" +
                "\t\t\t\t\t\t<!-- EMAIL BODY // -->\n" +
                "\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\tThe table \"emailBody\" is the email's container.\n" +
                "\t\t\t\t\t\t\tIts width can be set to 100% for a color band\n" +
                "\t\t\t\t\t\t\tthat spans the width of the page.\n" +
                "\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t<table bgcolor=\"#FFFFFF\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" id=\"emailBody\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- MODULE ROW // -->\n" +
                "\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\tTo move or duplicate any of the design patterns\n" +
                "\t\t\t\t\t\t\t\tin this email, simply move or copy the entire\n" +
                "\t\t\t\t\t\t\t\tMODULE ROW section for each content block.\n" +
                "\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\t\t\tThe centering table keeps the content\n" +
                "\t\t\t\t\t\t\t\t\t\ttables centered in the emailBody table,\n" +
                "\t\t\t\t\t\t\t\t\t\tin case its width is set to 100%.\n" +
                "\t\t\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"color:#FFFFFF;\" bgcolor=\"#3498db\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tThe flexible container has a set width\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tthat gets overridden by the media query.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tMost content tables within can then be\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tgiven 100% widths.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe content table is the first element\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tthat's entirely separate from the structural\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tframework of the email.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h1 style=\"color:#FFFFFF;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:35px;font-weight:normal;margin-bottom:5px;text-align:center;\">Presta Trading Post</h1>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h2 style=\"text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#205478;line-height:135%;\">The Ulitimate meetup spot for those who have and those who don't</h2>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;\">A registered Presta user has requested your item. Please accept or decline. Presta Trading Post will take care of the rest!!</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- // CONTENT TABLE -->\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // MODULE ROW -->\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- MODULE ROW // -->\n" +
                "\t\t\t\t\t\t\t<!--  The \"mc:hideable\" is a feature for MailChimp which allows\n" +
                "\t\t\t\t\t\t\t\tyou to disable certain row. It works perfectly for our row structure.\n" +
                "\t\t\t\t\t\t\t\thttp://kb.mailchimp.com/article/template-language-creating-editable-content-areas/\n" +
                "\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t<tr mc:hideable>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // MODULE ROW -->\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- MODULE ROW // -->\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr style=\"padding-top:0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"padding-top:0;\" align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"#\" target=\"_blank\">Accept</a>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "                              <br>\n" +
                "                              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"50%\" class=\"emailButton\" style=\"background-color: #3498DB;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"middle\" class=\"buttonContent\" style=\"padding-top:15px;padding-bottom:15px;padding-right:15px;padding-left:15px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a style=\"color:#FFFFFF;text-decoration:none;font-family:Helvetica,Arial,sans-serif;font-size:20px;line-height:135%;\" href=\"#\" target=\"_blank\">Decline</a>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- // CONTENT TABLE -->\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // MODULE ROW -->\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- MODULE ROW // -->\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#F8F8F8\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe \"mc:edit\" is a feature for MailChimp which allows\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tyou to edit certain row. It makes it easy for you to quickly edit row sections.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thttp://kb.mailchimp.com/templates/code/create-editable-content-areas-with-mailchimps-template-language\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 mc:edit=\"header\" style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">You have a request for your item!</h3>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div mc:edit=\"body\" style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante.</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- // CONTENT TABLE -->\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // MODULE ROW -->\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t<!-- MODULE ROW // -->\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- CENTERING TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- FLEXIBLE CONTAINER // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"30\" cellspacing=\"0\" width=\"500\" class=\"flexibleContainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" width=\"500\" class=\"flexibleContainerCell\">\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- CONTENT TABLE // -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" class=\"flexibleContainerBox\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width: 100%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">Left Column</h3>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis.</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"right\" valign=\"middle\" class=\"flexibleContainerBox\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"flexibleContainerBoxNext\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"210\" style=\"max-width: 100%;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" class=\"textContent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 style=\"color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;text-align:left;\">Right Column</h3>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"text-align:left;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;\">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis.</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- // CONTENT TABLE -->\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!-- // FLEXIBLE CONTAINER -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- // CENTERING TABLE -->\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<!-- // MODULE ROW -->\n";
        return htmlMesssageBody;
    }

}
