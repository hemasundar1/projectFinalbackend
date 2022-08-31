package com.example.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.models.ApplicationDetails;
import com.example.project.models.DocumentDetails;
import com.example.project.modelsDto.DocumentDto;
import com.example.project.repository.DocumentRepository;

@Service
public class DocumentService {

	
	@Autowired
	DocumentRepository docRepo;
	
	public List<DocumentDetails> getAlldocuments()
	{
		return (List<DocumentDetails>)docRepo.findAll();
	}
	
	/*public DocumentDetails getdocumentByappId(int appId)
	{
		Application_details app=new Application_details();
		
		return docRepo.findByappId(appId);
	}*/
	
	public DocumentDetails getdocumentsById(Integer id)
	{
		return docRepo.findById(id).get();
	}
	
	public Optional<DocumentDetails> getDet( int id){
		return docRepo.findById(id);
	}
	
	public String postDet(DocumentDto docDet,int appId) {
		
		DocumentDetails doc = new DocumentDetails(docDet.getAdharNum(),docDet.getAccountNum(),docDet.getIfscNum(),docDet.getBranch(),docDet.getRollNum());
		doc.setApplication_details(new ApplicationDetails(appId));
		if(docRepo.save(doc)!=null){
		  return "Successfully added document details";	
		}
		else {
			return  "Not, added Successfully";
		}
	}
	
	public String putDet(  DocumentDetails docDetails , int id) {
		
		Optional<DocumentDetails> docDet = docRepo.findById(id);
		DocumentDetails result = docDet.get();
		result.setAccountNum(docDetails.getAccountNum());
		result.setAdharNum(docDetails.getAdharNum());
		result.setBranch(docDetails.getBranch());
		result.setIfscNum(docDetails.getIfscNum());
		result.setRollNum(docDetails.getRollNum());
		if(docRepo.save(result) !=null) {
			return "Updated Successfully";
		}
		else {
			return  "Not Updated, something went wrong";
		}
	}
	
	public String deleteDet(int id) {
		try {
			docRepo.deleteById(id);
			return "Successfully deleted";
		}
		catch(Exception e) {
			return "Not deleted Successfully";
		}
	}
	
}
