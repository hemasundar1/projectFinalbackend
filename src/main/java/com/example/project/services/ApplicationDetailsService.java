package com.example.project.services;

import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpServletResponse;

import com.example.project.models.ApplicationDetails;
import com.example.project.models.UserModel;
import com.example.project.modelsdto.DocDto;
import com.example.project.repository.ApplicationDetailsRepository;

@javax.annotation.ParametersAreNonnullByDefault
@Service
public class ApplicationDetailsService {
	private static org.apache.log4j.Logger log = Logger.getLogger(ApplicationDetailsService.class); 

	@Autowired
	ApplicationDetailsRepository appRepo;
	
	public List<ApplicationDetails> getAllRequests()
	{
		return appRepo.findAll();
	}

	public ApplicationDetails getAppDetailsById(Integer id) {
		return appRepo.findByAppId(id);
	}
	
	public ApplicationDetails getByName(String name)
	{
		return appRepo.findByName(name);
	}
	
	public void updateApp(ApplicationDetails appUpdate)
	{
		Integer id=appUpdate.getAppId();
		ApplicationDetails appupdate=appRepo.findByAppId(id);
		appupdate.setName(appUpdate.getName());
		appupdate.setClassName(appUpdate.getClassName());
		appupdate.setCollegeName(appUpdate.getCollegeName());
		appupdate.setPostalAddress(appUpdate.getPostalAddress());
		appupdate.setState(appUpdate.getState());
		appRepo.save(appupdate);
	}
	
	public void requestApprove(Integer id,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findByAppId(id);
		appdetails.setStatus(appDetails.getStatus());
		appdetails.setStatus("approved");
		appRepo.save(appdetails);
	}
	
	public void requestReject(Integer id,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findByAppId(id);
		appdetails.setStatus(appDetails.getStatus());
		appdetails.setStatus("rejected");
		appRepo.save(appdetails);
	}
	
	public void assigned(Integer appId,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findByAppId(appId);
		appdetails.setAssigned(appDetails.getAssigned());
		appRepo.save(appdetails);
	}
	
	
	public void postAppDetails(ApplicationDetails app) {
		
			appRepo.save(app);
		

	}
	
	public void putAppDetails(ApplicationDetails app, int id) {
		ApplicationDetails appDetails = appRepo.findByAppId(id);
		appDetails.setClassName(app.getClassName());
		appDetails.setCollegeName(app.getCollegeName());
		appDetails.setName(app.getName());
		
		appDetails.setPostalAddress(app.getPostalAddress());
		appDetails.setState(app.getState());
		appRepo.save(appDetails);
	}
	
	public String deleteAppDetails(int id) {
		
		try {
			appRepo.deleteById(id);
			return "Deleted Successfully";
			
		}
		catch(Exception e) {
			return "Not Deleted";
		}
		
	}
	@javax.annotation.CheckForNull
	public Integer uploadFile(MultipartFile multipartFile, String name,String postalAddress,String collegeName, String state, String className,int studentId) throws IOException {
		
		String fileName;
		fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String url = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFromDB")
				.path(fileName)
				.toUriString();
		
		ApplicationDetails appDetails = new ApplicationDetails(fileName,name, collegeName, state,className, url,multipartFile.getBytes());
		appDetails.setPostalAddress(postalAddress);
		appDetails.setUserModel(new UserModel(studentId));
		appDetails.setDocumentType("JPEG");
		appRepo.save(appDetails);
		
		
	

		return appDetails.getAppId();
	}
	
	public byte[] getFile(int appId,HttpServletResponse request) {

		ApplicationDetails doc =  appRepo.findByAppId(appId);

		request.setHeader("Content-Disposition", "attachment; filename=" + doc.getImage());
		log.info("uploadfile working");
		return doc.getDocument();			
	}
	public DocDto fileType(int appId) {
		ApplicationDetails doc =  appRepo.findByAppId(appId);
		DocDto documentDto = new DocDto();
	     documentDto.setDocumentType(doc.getDocumentType());
		documentDto.setImage(doc.getImage());
		log.info("fileType");
		return documentDto;

	}


}
