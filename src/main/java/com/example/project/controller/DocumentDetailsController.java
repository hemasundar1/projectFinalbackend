package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.services.DocumentService;
import com.example.project.models.DocumentDetails;
import com.example.project.modelsdto.DocumentDto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DocumentDetailsController {
	
	@Autowired
	DocumentService docSer;
	
	
	
	@GetMapping("/employee/documents")
	public List<DocumentDetails> getAlldocuments()
	{
		return docSer.getAlldocuments();
	}
	
	@GetMapping("/employee/documents/{id}")
	public DocumentDetails getdocumentsById(@PathVariable Integer id)
	{
		return docSer.getdocumentsById(id);
	}
	
	
	@GetMapping("/docDetails")
	public Optional<DocumentDetails> getDocDet(@RequestParam(value="id", required = true) int id){
		return docSer.getDet(id);
		
	}
	
	@PostMapping("/docDetails")
	public void postDocDet(@RequestBody DocumentDto docDet, @RequestParam(value ="id" , required = true) int appId) {
		docSer.postDet(docDet,appId);
	}
	
	@DeleteMapping("/docDetails")
	public String deleteDocDet(@RequestParam(value="id", required = true) int id) {
		return docSer.deleteDet(id);
	}

}
