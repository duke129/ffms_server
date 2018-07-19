package com.hm.product.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hm.dao.mysql.product.ProductDao;
import com.hm.util.model.APIResponse;
import com.hm.util.model.ImageDTO;
import com.hm.util.model.ProductDTO;

@Service
public class ProductManagerImpl implements ProductManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductManagerImpl.class);

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<ProductDTO> findProductByAssetId(Long assetTypeId) {
		List<ProductDTO> productdtoList=productDao.findAllProductByAssetId(assetTypeId);
		return productdtoList;
	}

	@Override
	public List<ImageDTO> findProductImageListByProductId(String modelId) {
		ProductDTO productdto=productDao.findProductImageListByProductId(Long.valueOf(modelId));
		String imageFolderPath=productdto.getImgPath();
		File folder = new File(imageFolderPath);
		File[] listOfImageFiles = folder.listFiles();
		
		List<ImageDTO> imageList=new ArrayList<ImageDTO>();
		
		if(listOfImageFiles != null)
		{
			for (int i = 0; i < listOfImageFiles.length; i++) {
		    	ImageDTO imageDTO=new ImageDTO();
		      if (listOfImageFiles[i].isFile()) {
		        String fileName=listOfImageFiles[i].getName();
		       String extensionRemoved = fileName.split("\\.")[0];
		        imageDTO.setName(extensionRemoved);
		        imageDTO.setImage(encodeFileToBase64Binary(new File(imageFolderPath+fileName)));
		      } 
		      imageList.add(imageDTO);
		    }
		}
		
		    
		return imageList;
	}

	  private static String encodeFileToBase64Binary(File file){
           String encodedfile = null;
           try {
               @SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(file);
               byte[] bytes = new byte[(int)file.length()];
               fileInputStreamReader.read(bytes);
               //encodedfile = Base64.encodeBase64(bytes).toString();
               encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
              
           } catch (IOException e) {
               e.printStackTrace();
              
           }

           return encodedfile;
       }

	@Override
	public APIResponse getImageByProductId(Long id) {
		
		String base64Image = productDao.getImageByProductId(id);
		
		APIResponse apiResponse = new APIResponse();
		
		if(base64Image != null)
		{
			apiResponse.setStatusId(200);
			apiResponse.setStatusMessage("Success");
			apiResponse.setData(base64Image);
			
			return apiResponse;
		}
		
		apiResponse.setStatusId(303);
		apiResponse.setStatusMessage("Failed to get image");
		
		return apiResponse;
	}
	

	
}
