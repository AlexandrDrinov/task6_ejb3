package by.epamlab.model.factories;

import by.epamlab.ifaces.IUserDAO;
import by.epamlab.model.impl.UserImplDB;
import by.epamlab.model.impl.UserImplMemory;


public class UserFactory {
	public static IUserDAO getClassFromFactory() {
		//return new UserImplMemory ();
                return new UserImplDB();
	}
}
