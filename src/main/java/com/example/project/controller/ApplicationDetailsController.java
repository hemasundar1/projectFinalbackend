package com.example.project.controller;

import org.apache.log4j.*;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.services.ApplicationDetailsService;
import com.example.project.models.ApplicationDetails;
import com.example.project.modelsdto.ApplicationdetailsDTO;
import com.example.project.modelsdto.DocDto;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ApplicationDetailsController {
	private static org.apache.log4j.Logger log =Logger.getLogger(ApplicationDetailsController.class);
	
	@Autowired
	ApplicationDetailsService appSer;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/employee/requests")
	public List<ApplicationDetails> getAllRequests()
	{
		return appSer.getAllRequests();
	}
	
	@GetMapping("/employee/requests/{id}")
	public ApplicationDetails getAppDetailsByID(@PathVariable Integer id){
		return appSer.getAppDetailsById(id);
	}
	
	@GetMapping("/employee/search/{name}")
	public ApplicationDetails getByName(@PathVariable String name)
	{
		return appSer.getByName(name);
	}
	
	@PutMapping("/employee/requestApprove/{id}")
	public void requestApprove(@PathVariable Integer id,@RequestBody ApplicationdetailsDTO app)
	{
		ApplicationDetails appdetails=modelMapper.map(app,ApplicationDetails.class);
		appSer.requestApprove(id, appdetails);
	}
	
	@PutMapping("/employee/requestReject/{id}")
	public void requestReject(@PathVariable Integer id,@RequestBody ApplicationdetailsDTO app)
	{
		ApplicationDetails appdetails=modelMapper.map(app, ApplicationDetails.class);
		appSer.requestReject(id, appdetails);
	}
	
	@PutMapping("/request/assign/{id}")
	public void assignedTo(@PathVariable Integer id,@RequestBody ApplicationdetailsDTO app)
	{
		ApplicationDetails assign=modelMapper.map(app, ApplicationDetails.class);
		appSer.assigned(id, assign);
	}
	
	@PutMapping("/request/student")
	public void updateApp(@RequestBody ApplicationdetailsDTO app)
	{
		ApplicationDetails appdetails=modelMapper.map(app, ApplicationDetails.class);
		appSer.updateApp(appdetails);
	}
	
	@PostMapping("/appDetails")
	public void postAppDet(@RequestBody ApplicationdetailsDTO appDet) {
		ApplicationDetails app= modelMapper.map(appDet, ApplicationDetails.class);
		appSer.postAppDetails(app);
	}
	
	@DeleteMapping("/appDetails")
	public String deleteAppDet(@RequestParam(value="id", required = true) int id) {
		return appSer.deleteAppDetails(id);
	}
	
	@PostMapping("/uploadFile")
	public int uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("name") String name,@RequestParam("postalAddress") String postalAddress,@RequestParam("collegeName") String collegeName,@RequestParam("state") String state,@RequestParam("className") String className, @RequestParam("studentId") int studentId) throws IOException{
		 return appSer.uploadFile(multipartFile, name,postalAddress,collegeName,state,className,studentId);
	  }
	
	  
	  @GetMapping("/downloadFromDB")
		public void downloadFile(@RequestParam(value = "id" , required = true) int  appId,HttpServletResponse response){
			try {
				response.getOutputStream().write(fileContent(appId, response));
			}catch(Exception e)
			{
				log.info(e);
			}
		}
	  
	  private byte[] fileContent(int appId,HttpServletResponse response) {
			return appSer.getFile(appId,response);
		}
	  
	  @GetMapping("/fileType")
		public DocDto fileType(@RequestParam(value = "id", required = true) int appId) {
			return appSer.fileType(appId);
		}
}
