package com.example.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.models.ApplicationDetails;
import com.example.project.models.DocumentDetails;
import com.example.project.modelsdto.DocumentDto;
import com.example.project.repository.DocumentRepository;

@Service
public class DocumentService {

	
	@Autowired
	DocumentRepository docRepo;
	
	public List<DocumentDetails> getAlldocuments()
	{
		return docRepo.findAll();
	}
	
	
	public DocumentDetails getdocumentsById(Integer id)
	{
		return docRepo.findByid(id);
	}
	
	public Optional<DocumentDetails> getDet( int id){
		return docRepo.findById(id);
	}
	
	public void postDet(DocumentDto docDet,int appId) {
		
		DocumentDetails doc = new DocumentDetails(docDet.getAdharNum(),docDet.getAccountNum(),docDet.getIfscNum(),docDet.getBranch(),docDet.getRollNum());
		doc.setApplicationdetails(new ApplicationDetails(appId));
		docRepo.save(doc);
	}
	
	public void putDet(  DocumentDetails docDetails , int id) {
		
	 DocumentDetails result = docRepo.findByid(id);
		result.setAccountNum(docDetails.getAccountNum());
		result.setAdharNum(docDetails.getAdharNum());
		result.setBranch(docDetails.getBranch());
		result.setIfscNum(docDetails.getIfscNum());
		result.setRollNum(docDetails.getRollNum());
		docRepo.save(result);
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
