package org.zerocouplage.application.mobile.validateClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.zerocouplage.api.validator.IZCValidator;
import org.zerocouplage.application.mobile.bean.AdminBeanIn;
import org.zerocouplage.application.mobile.business.BD;

public class AdminValidate implements IZCValidator {
	private static final String GLOBAL_MESSAGE = "Some fields are required";
 
	
	public Map<String, String> validate(Map<Object, Object> ViewValue,
			Map<String, String> propertiesMap, Object beanIn) {

		BD db = null;
		AdminBeanIn Ibean = (AdminBeanIn) beanIn;
		System.out.println("the bean is "+Ibean.getLogin_admin());
	
		Map<String, String> listeErrors = new HashMap<String, String>();
		try {
			db = new BD();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			
			ResultSet rs=db.query("SELECT * FROM administrateur");
			System.out.println(rs);
			while(rs.next()){
				
				if(!rs.getString("login").equals(Ibean.getLogin_admin())){
					listeErrors.put("error_login", "ce login n'existe pas");
				}
				if(!rs.getString("pass").equals(Ibean.getPassword_admin())){
					listeErrors.put("error_password","le mot de passe est incorrecte");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!listeErrors.isEmpty()) {
			listeErrors.put("GLOBAL_MESSAGE", GLOBAL_MESSAGE);
		}
		return listeErrors;
	}
}
