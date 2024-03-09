package com.tcs.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.service.StorageService;

@RestController
@RequestMapping("/api")

public class StorageRestController {

	@Autowired
	private StorageService storageService;

	@PostMapping("/uploadImg")
	public ResponseEntity<?> uploadImg(@RequestParam("img") MultipartFile file) throws IOException {
		
		System.out.println("Request comes  to rest controller post Api ::"+ file);
		String uploadImg = storageService.uploadImg(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(uploadImg);
	}
	
	@GetMapping("/downloadImg/{name}")
	public ResponseEntity<?> downloadImg(@PathVariable String name){
		
		System.out.println("Request comes  to rest controller get Api ::"+ name);

		byte[] downloadImg = storageService.downloadImg(name);
		return ResponseEntity.status(HttpStatus.OK)
				//what format we are excepting img
				.contentType(MediaType.valueOf("image/png"))
				.body(downloadImg);
	}

}
