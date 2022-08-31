package com.example.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.project.models.ApplicationDetails;
import com.example.project.models.UserModel;
import com.example.project.modelsDto.DocDto;
import com.example.project.repository.ApplicationDetailsRepository;

@Service
public class ApplicationDetailsService {

	@Autowired
	ApplicationDetailsRepository appRepo;
	
	public List<ApplicationDetails> getAllRequests()
	{
		return (List<ApplicationDetails>)appRepo.findAll();
	}

	public ApplicationDetails getAppDetailsById(Integer id) {
		return appRepo.findById(id).get();
	}
	
	public void updateApp(ApplicationDetails appUpdate)
	{
		Integer id=appUpdate.getAppId();
		ApplicationDetails appupdate=appRepo.findById(id).get();
		appupdate.setFirstName(appUpdate.getFirstName());
		appupdate.setLastName(appUpdate.getLastName());
		appupdate.setClassName(appUpdate.getClassName());
		appupdate.setCollegeName(appUpdate.getCollegeName());
		appupdate.setPostalAddress(appUpdate.getPostalAddress());
		appupdate.setState(appUpdate.getState());
		appRepo.save(appUpdate);
	}
	
	public void requestApprove(Integer id,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findById(id).get();
		appdetails.setStatus("approved");
		appRepo.save(appdetails);
	}
	
	public void requestReject(Integer id,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findById(id).get();
		appdetails.setStatus("rejected");
		appRepo.save(appdetails);
	}
	
	public void assigned(Integer appId,ApplicationDetails appDetails)
	{
		ApplicationDetails appdetails=appRepo.findById(appId).get();
		appdetails.setAssigned(appDetails.getAssigned());
		appRepo.save(appdetails);
	}
	
	
	public int postAppDetails(ApplicationDetails app) {
		
		if(appRepo.save(app)!=null) {
			return app.getAppId() ;
		}
		else {
			return 0;
		}
	}
	
	public String putAppDetails(ApplicationDetails app, int id) {
		Optional<ApplicationDetails> result = appRepo.findById(id);
		ApplicationDetails appDetails = result.get();
		appDetails.setClassName(app.getClassName());
		appDetails.setCollegeName(app.getCollegeName());
		appDetails.setFirstName(app.getFirstName());
		appDetails.setLastName(app.getLastName());
		
		appDetails.setPostalAddress(app.getPostalAddress());
		appDetails.setState(app.getState());
		
		
		
		if(appRepo.save(appDetails) !=null) {
			
			 return "Successfully updated";
		}
		else {
			return "Not updated successfully";
		}
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
	
	public int uploadFile(MultipartFile multipartFile, String firstName,String lastName,String postalAddress,String collegeName, String state, String className,int studentId) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String url = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFromDB")
				.path(fileName)
				.toUriString();
		
		ApplicationDetails appDetails = new ApplicationDetails(fileName, firstName, lastName, postalAddress, collegeName, state,className, url,multipartFile.getBytes());
		appDetails.setUserModel(new UserModel(studentId));
		appDetails.setDocumentType("JPEG");
		appRepo.save(appDetails);
		
		
		String contentType = multipartFile.getContentType();

		return appDetails.getAppId();
	}
	
	public byte[] getFile(int appId,HttpServletResponse request) {

		ApplicationDetails doc =  appRepo.findByAppId(appId);

		request.setHeader("Content-Disposition", "attachment; filename=" + doc.getImage());
		System.out.println("uploadfile working");
		return doc.getDocument();			
	}
	public DocDto fileType(int appId) {
		ApplicationDetails doc =  appRepo.findByAppId(appId);
		DocDto documentDto = new DocDto();
	     documentDto.setDocumentType(doc.getDocumentType());
		documentDto.setImage(doc.getImage());
		System.out.println("fileType");
		return documentDto;

	}


}
