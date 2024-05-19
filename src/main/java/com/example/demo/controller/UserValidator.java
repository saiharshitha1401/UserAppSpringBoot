package com.example.demo.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.demo.models.UserDTO;


@Component
public class UserValidator {
   public String isValid(UserDTO user) {
	   if(user.getFull_name() == "") {
		   return "username cannot be empty";
	   }
	   if(user.getMob_num().length() != 10) {
		   return "mobile no must be 10 digits";
	   }
	
       String panRegex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

       Pattern pattern = Pattern.compile(panRegex);
       Matcher matcher = pattern.matcher(user.getPan_num());
       if(!matcher.matches()) {
    	   return "pan number is invalid";
       }
	   return "";
   }
}
