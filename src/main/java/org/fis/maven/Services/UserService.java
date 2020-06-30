package org.fis.maven.Services;


import org.fis.maven.Models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;

public class UserService {
    private static ArrayList<User> u = new ArrayList<>();
    private static String path;

    public static void loadUser(){
        u = new ArrayList<>();

        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader(path);
            Object obj = jp.parse(fr);
            JSONArray ja = (JSONArray)obj;

            for(Object user:ja){
                JSONObject o = (JSONObject) user;

                String name = o.get("name").toString();
                String username = o.get("username").toString();
                String password = o.get("password").toString();
                String mail = o.get("mail").toString();
                String role = o.get("role").toString();
                int credit = Integer.parseInt(o.get("credit").toString());
                String status = o.get("status").toString();
                boolean confirmed = Boolean.parseBoolean(o.get("confirmed").toString());
                String city = o.get("city").toString();

                u.add(new User(name, username, password, mail, role, credit, status, confirmed, city));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static boolean checkCredentials(String id, String pass){
        boolean found = false;

        for(User i:u){
            if(i.getUsername().equals(id) && i.getPassword().equals(pass) && i.isConfirmed()){
                found=true;
                break;
            }
        }


        return found;
    }

    public static String encodePassword(String password){
        String result = Base64.getEncoder().encodeToString(password.getBytes());

        return result;
    }

    public static ArrayList<User> getU() {return u;}

    public static void writeUser() {
        FileWriter fw = null;
        try{
            fw = new FileWriter(path);

            JSONArray ja = new JSONArray();

            for(User i:u){
                JSONObject jo = new JSONObject();
                jo.put("name",i.getName());
                jo.put("username",i.getUsername());
                jo.put("password",i.getPassword());
                jo.put("mail",i.getMail());
                jo.put("role",i.getRole());
                jo.put("credit",String.valueOf(i.getCredit()));
                jo.put("status",i.getStatus());
                jo.put("confirmed",String.valueOf(i.isConfirmed()));
                jo.put("city",i.getCity());

                ja.add(jo);
            }

            fw.write(ja.toJSONString());

        }catch (Exception e){

        }finally {
            try{
                fw.flush();
                fw.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void setPath(String path) {
        UserService.path = path;
    }
}
