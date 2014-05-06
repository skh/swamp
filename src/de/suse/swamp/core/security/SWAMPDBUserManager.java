/*
 * SWAMP Workflow Administration and Management Platform
 *
 * Copyright (c) 2004 Sonja Krause-Harder <skh [at] suse.de>
 * Copyright (c) 2006 Novell Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of version 2 of the GNU General Public
 * License as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA 
 *
 * In addition, as a special exception, Novell Inc. gives permission to link the
 * code of this program with the following applications:
 *
 * - All applications of the Apache Software Foundation 
 *
 * and distribute such linked combinations.
 */

package de.suse.swamp.core.security;

/**
 * Implementation of a DB authentication.
 *
 * @author Thomas Schmidt <tschmidt@suse.de>
 * @version $Id$
 *
 */
import java.util.*;

import de.suse.swamp.core.container.*;
import de.suse.swamp.core.util.*;
import de.suse.swamp.util.*;

import java.io.*;
import java.security.*;

public class SWAMPDBUserManager implements UserManagerIface {

    
    public void authenticateUser(String loginName, String password) 
    	throws StorageException, UnknownElementException, PasswordException {
	    SWAMPUser user = null;
	    user = loadUser(loginName);
        String hash = toMD5(password);
	    if (!hash.equals(user.getPasswordHash())){
	    	Logger.ERROR("Failed authentification for user: " + user.getUserName());
	    	throw new PasswordException("Failed authentification for user: " + 
	    			user.getUserName());
	    }
    }

    
    public static String toMD5(String string) throws PasswordException {
    	MessageDigest md5 = null;
    	try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            throw new PasswordException("MD5 Algorithm not found: " + e.getMessage());
        }
        String hash = toHexString(md5.digest(string.getBytes()));
    	return hash;
    }
    
    
    
    /**
     * Loads the user from the db-backend. 
     * username=anonymous is treated special, as it is 
     * used for not-authenticated users to get unprotected content.
     */
    public SWAMPUser loadUser(String userName) throws StorageException, NoSuchElementException {
        // load from db:
        if (userName == null || userName.equals("")){
            throw new NoSuchElementException("Tried to load user with empty name!");
        }
        SWAMPUser user = null;
        if (userName.equalsIgnoreCase("anonymous")){ 
        	user = new SWAMPUser();
        	user.setUserName(userName);       	
        } else {
	        user = SecurityStorage.loadUserFromDB(userName);
	        if (user == null) {
	                throw new NoSuchElementException("User " + userName + 
	                		" does not exist");
	        }
        }
        return user;
    }
	
    
    public String toString(){
        return "SWAMPDBUserManager";   
    }
    
    
    /**
     * convert the byte array to 2-byte hex string
     */
    private static String toHexString(byte[] v) {
        final String HEX_DIGITS = "0123456789abcdef";
        StringBuffer sb = new StringBuffer(v.length * 2);
        for (int i = 0; i < v.length; i++) {
             int b = v[i] & 0xFF;
             sb.append(HEX_DIGITS.charAt(b >>> 4))
               .append(HEX_DIGITS.charAt(b & 0xF));
        }
        return sb.toString();
    }        
    
    
    /**
     * create md5 from file
     */
    private byte[] messageDigest(String file) throws Exception {
      MessageDigest messagedigest = MessageDigest.getInstance("MD5");
      byte md[] = new byte[8192];
      InputStream in  = new FileInputStream( file );

       for ( int n = 0; (n = in.read(md)) > -1; )
         messagedigest.update( md, 0, n );

      return messagedigest.digest();
    }
    
}