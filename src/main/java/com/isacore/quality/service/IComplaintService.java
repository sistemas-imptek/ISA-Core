package com.isacore.quality.service;

import java.util.List;

import com.isacore.quality.model.Complaint;
import com.isacore.quality.model.Product;
import com.isacore.util.CRUD;

public interface IComplaintService extends CRUD<Complaint> {
	
	List<Complaint> findAllCustomize();
}
