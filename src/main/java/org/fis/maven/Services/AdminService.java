package org.fis.maven.Services;


import org.fis.maven.Models.Admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;

public class AdminService {
    private static ArrayList<Admin> a = new ArrayList<>();
    private static String path;

    public static void loadAdmins(){
        a.clear();

        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader(path);
            Object obj = jp.parse(fr);
            JSONArray ja = (JSONArray)obj;

            for(Object admin:ja){
                JSONObject o = (JSONObject) admin;
                String name = o.get("name").toString();
                String id = o.get("username").toString();
                String pass = o.get("password").toString();
                String mail = o.get("mail").toString();
                a.add(new Admin(name,id,pass,mail));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void writeAdmins() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);

            JSONArray ja = new JSONArray();

            for (Admin i : a) {
                JSONObject jo = new JSONObject();
                jo.put("name", i.getName());
                jo.put("username", i.getUsername());
                jo.put("password", i.getPassword());
                jo.put("mail", i.getMail());

                ja.add(jo);
            }

            fw.write(ja.toJSONString());

        } catch (Exception e) {

        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public static String encodePassword(String s){
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public static void setPath(String path) {
        AdminService.path = path;
    }

    public static ArrayList<Admin> getA() {
        return a;
    }
}
