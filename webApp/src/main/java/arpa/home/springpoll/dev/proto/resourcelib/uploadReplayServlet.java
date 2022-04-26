package arpa.home.springpoll.dev.proto.resourcelib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;



/*
 * Servlet Prototype, requires to be registered as servlet
 * 	@Bean
	public ServletRegistrationBean exampleServletBean() {
	    ServletRegistrationBean bean = new ServletRegistrationBean(
	      new uploadReplayServlet(), "/lib/upload");
	    bean.setLoadOnStartup(1);
	    
	    bean.setMultipartConfig(new MultipartConfigElement
	    		("/tmp", 20971520L,41943040L, 52428080)); 
	    return bean;
	}
 */
public class uploadReplayServlet extends HttpServlet {
	
	private volatile int TICKET_ID_SEQUENCE = 1;

  private Map<Integer, Attachment> attachmentDatabase = new LinkedHashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String action = request.getParameter("action");
		System.out.println("You are getting " + action);
		if(action == null) {
			action = "list";
		}
		switch(action) {
			case "list":{
				break;
			}
		  case "download": {
		  	this.downloadAttachment(request, response);
		  	break;
		  }
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name;
		Enumeration<String> allParams = request.getParameterNames();
		while(allParams.hasMoreElements()) {
			name = allParams.nextElement();
			System.out.println(name);
		}
		
		String action = request.getParameter("action");
		System.out.println("You posted " + action);
		
		if(action == null) {
			action = "list";
		}
		switch(action) {
			case "create":{
				this.createAttachment(request);
				break;
			}
			default:
				response.sendRedirect("upload");
				break;
		}
	}
	
	public void createAttachment(HttpServletRequest request) throws IOException, ServletException {
	
		
		for(Part p : request.getParts()) {
			System.out.println(p.getSubmittedFileName());
		}
		System.out.println("Tried to create");
		Part filePart = request.getPart("file1");
		if(filePart==null) {
			return;
		}
		System.out.println("You created a " + filePart.getSubmittedFileName());

		if(filePart != null && filePart.getSize() > 0) {
			Attachment attachment = this.processAttachment(filePart);
		  int id;
	    synchronized(this)
	    {
	      id = this.TICKET_ID_SEQUENCE++;
	      System.out.println("id registered in db:" + id);
	      this.attachmentDatabase.put(id, attachment);
	    }
		}
	}
	
	 private Attachment processAttachment(Part filePart)
       throws IOException
{
   InputStream inputStream = filePart.getInputStream();
   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

   int read;
   final byte[] bytes = new byte[1024];

   while((read = inputStream.read(bytes)) != -1)
   {
       outputStream.write(bytes, 0, read);
   }

   Attachment attachment = new Attachment();
   attachment.setName(filePart.getSubmittedFileName());
   attachment.setContents(outputStream.toByteArray());

   return attachment;
}
	
	
	public void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String idString = request.getParameter("attachId");
		 System.out.println("With id " + idString);
		 
		 Attachment attachment = attachmentDatabase.get(Integer.parseInt(idString));
		 if(attachment == null) {
			 System.out.println("Supplied Id not in database");
			 return;
		 }
		 String name = request.getParameter("attachName");
		 response.setHeader("Content-Disposition",
         "attachment; filename=" + attachment.getName());
		 response.setContentType("application/octet-stream");
		 
		 ServletOutputStream stream = response.getOutputStream();
     stream.write(attachment.getContents());

		 
	}
	
	
}
