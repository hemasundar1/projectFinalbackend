package com.example.project.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;



@CrossOrigin(origins = "http://localhost:4200")

@Entity
public class ApplicationDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int appId;
	private String image;
	private String name;
	private String postalAddress;
	private String collegeName;
	private String state;
	private String className;
	private String url;
	private String status;
	private int assigned;
	private String documentType;
	
	
	@Lob
	private byte[] document;
	
	
	
	@OneToOne(mappedBy= "applicationDetails" , fetch = FetchType.EAGER , orphanRemoval = true)
	@JsonIgnore
	private DocumentDetails documentDetails;
	
	
	
	@OneToOne(fetch= FetchType.EAGER, optional = false)
	@JoinColumn(name="studentId", referencedColumnName = "userId")
	private UserModel userModel;
	
	public int getAssigned() {
		return assigned;
	}

	public void setAssigned(int assigned) {
		this.assigned = assigned;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public ApplicationDetails(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public DocumentDetails getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails = documentDetails;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public ApplicationDetails( String image,String name, 
			String collegeName, String state, String className,  String url,
			byte[] document) {
		super();
		
		this.image = image;
		this.name=name;
		this.collegeName = collegeName;
		this.state = state;
		this.className = className;
		this.url = url;
		this.document = document;
		
	}
	public ApplicationDetails()
	{
		super();
	}
}
