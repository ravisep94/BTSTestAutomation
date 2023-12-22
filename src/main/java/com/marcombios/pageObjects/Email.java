/**
 * 
 */
package com.marcombios.pageObjects;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.testng.Assert;

import com.marcombios.utilities.Base;



public class Email extends Base {
	public void verifyMail(String user_Name, String passwd, String message) throws MessagingException, IOException{
		Session session = Session.getDefaultInstance(new Properties( ));
	    Store store = session.getStore("imaps");
	    store.connect("imap.outlook.com",993, user_Name, passwd);
	    Folder inbox = store.getFolder( "INBOX" );
	    inbox.open( Folder.READ_WRITE );

	    // Fetch unseen messages from inbox folder
	    Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
	    Arrays.sort( messages);
	    // Sort messages from recent to oldest
	    /*Arrays.sort( messages, ( m1, m2 )-> {
	      try {
	        return m2.getSentDate().compareTo( m1.getSentDate() );
	      } catch ( MessagingException e ) {
	        throw new RuntimeException( e );
	      }
	    } );*/
	    
System.out.println("total messages = "+messages.length);
	    for ( Message mail : messages ) {
	    	String value = mail.getSubject();
	    	String content =  mail.getDescription();
	    	System.out.println("subject found : "+value);
    		logger.info("Print the Content");
    		System.out.println("content found : "+content);
	   if(value.contains(message)) {
		   		mail.getContent();
		   		
	    		System.out.println("subject found : "+value);
	    		logger.info("Print the Content");
	    		System.out.println("content found : "+content);
	    		
	    		 //Assert.assertTrue(value.contains(message),"No Email Found");
	    		 
	    		// inbox.close(false);
	    	}
	   else {
		   Assert.assertTrue(value.contains(message),"No Email Found");
   		  
	   }
	}
}
}
