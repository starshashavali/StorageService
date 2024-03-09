package com.tcs.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.domain.ImageData;
import com.tcs.repo.StorageRepo;
import com.tcs.utils.ImgUtils;

@Service
public class StorageService {

	@Autowired
	private StorageRepo storageRepo;

	// upload img

	public String uploadImg(MultipartFile file) throws IOException {

		// return obj data
		ImageData saveStatus = storageRepo.save(ImageData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).img(ImgUtils.compressImage(file.getBytes())).build());

		if (saveStatus != null) {
			return "File uploaded successfully..." + file.getOriginalFilename();
		}
		return null;

		
	}

	// fetch img
public byte[] downloadImg(String name) {
	Optional<ImageData> dbImg = storageRepo.findByName(name);
	byte[] images = ImgUtils.decompressImage(dbImg.get().getImg());
	return images;
	
}
}
