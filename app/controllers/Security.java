package controllers;

import models.*;
import models.AdminUser;

public class Security extends Secure.Security {

    static boolean authentify(String username, String password) {
        return AdminUser.connect(username, password) != null;
    }
    
    static boolean check(String profile) {System.out.println("cccc"+"admin".equals(profile));
        if("admin".equals(profile)) {
        	System.out.println("ccssssss"+AdminUser.find("byUserName", connected()).<AdminUser>first().isAdmin);
            return AdminUser.find("byUserName", connected()).<AdminUser>first().isAdmin;
        }
        return false;
    }
    
    static void onDisconnected() {
        Application.index();
    }
    
    static void onAuthenticated() {
        Admin.index();
    }
    
}

