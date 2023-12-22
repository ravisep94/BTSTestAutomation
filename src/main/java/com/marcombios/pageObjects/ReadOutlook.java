package com.marcombios.pageObjects;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.search.ItemView;

import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;
import org.springframework.util.CollectionUtils;
import org.testng.annotations.BeforeMethod;

import com.marcombios.utilities.Base;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
public class ReadOutlook extends Base {
	 public  ExchangeService createConnection(String email, String password) throws Exception {
	    	/* try {
	             ExchangeService service = createConnection(email,password);
	             readAttachmentEmail(service);
	         } catch (Exception e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }*/
	        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
	        ExchangeCredentials credentials = new WebCredentials(email, password);
	        service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));
	        service.setCredentials(credentials);
	        return service;
	    }

	    public void readEmail(ExchangeService service, String subject) throws Exception {
	        // Bind to the Inbox.
	        Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
	        // set number of items you want to retrieve
	        ItemView view = new ItemView(1);
	        List<SearchFilter> searchFilterCollection = new ArrayList<>();
	        // flag to pick only email which contains attachments
	        searchFilterCollection.add(new SearchFilter.IsEqualTo(ItemSchema.HasAttachments, Boolean.FALSE));
	        searchFilterCollection.add(new SearchFilter.ContainsSubstring(ItemSchema.Subject,subject));
	        SearchFilter finalSearchFilter = new SearchFilter.SearchFilterCollection(LogicalOperator.And, searchFilterCollection);
	        ArrayList<Item> items =  service.findItems(inbox.getId(), finalSearchFilter, view).getItems();
	        if(!CollectionUtils.isEmpty(items)) {
	            Item item= items.get(0);
	            System.out.println("id==========" + item.getDateTimeReceived());
	            System.out.println("sub==========" + item.getSubject());
	            /*EmailMessage message = EmailMessage.bind(service, item.getId(), new PropertySet(ItemSchema.Attachments));
	            FileAttachment attachment = (FileAttachment) message.getAttachments().getItems().get(0);
	            attachment.load(" < attachment save directory > "+attachment.getName());*/
	        }
	        else
	        {
	        	logger.info("No matching mails");
	        }

	    }
}
