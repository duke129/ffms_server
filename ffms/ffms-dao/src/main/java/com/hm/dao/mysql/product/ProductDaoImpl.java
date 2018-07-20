/**
 * 
 */
package com.hm.dao.mysql.product;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.Product;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TicketDetails;



/**
 * @author kiran
 *
 */

@Repository
public class ProductDaoImpl implements ProductDao{

	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	
	private static final String model_Details_Query="select p.idProduct,p.name,p.price,p.assetTypeId,p.imgPath from Product p where p.assetTypeId=?";
	private static final String getmodelImageFolder_Query="select p.videoPath from Product p where p.idProduct=?";
	
	private static final String GET_IMAGE_PATH = " select p.imgPath from Product p where p.idProduct = :productId ";
	
	@Autowired
	ProductRepository productRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ProductDTO> findAllProductDetails() {
		List<Product> listOfProduct =productRepository.findAll();
		logger.info("Product details :::::: "+listOfProduct);
			
			List<ProductDTO> productList=listOfProduct.stream().map(product ->{
			return convertIntoTypeHeadVo(product);
			}).collect(Collectors.toList());
			return productList;
	}
		public ProductDTO convertIntoTypeHeadVo(Product product) {
			
			ProductDTO productDTO=new ProductDTO();
			productDTO.setIdProduct(String.valueOf(product.getIdProduct()));
			productDTO.setName(product.getName());
			productDTO.setDescription(product.getDescription());
			//productDTO.setAssetId(product.getAssetId());
			productDTO.setPrice(product.getPrice());
			productDTO.setImgPath(product.getImgPath());
			productDTO.setVideoPath(product.getVideoPath());
			productDTO.setImage(encodeFileToBase64Binary(new File(product.getImgPath())));
			return productDTO;
		
	  }

	private String encodeFileToBase64Binary(File file) {
		logger.info("file is ::" + file);
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			// encodedfile = Base64.encodeBase64(bytes).toString();
			encodedfile = new String(Base64.getEncoder().encode(bytes), "UTF-8");
			fileInputStreamReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return encodedfile;
	}
	
	@Override
	public List<ProductDTO> findAllProductByAssetId(Long id) {

		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

		List<Object[]> product = entityManager.createNativeQuery(model_Details_Query).setParameter(1, id)
				.getResultList();

		for (Object[] objects : product) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setIdProduct(String.valueOf(objects[0]));
			productDTO.setName(String.valueOf(objects[1]));
			productDTO.setPrice(String.valueOf(objects[2]));
			productDTO.setAssetTypeId(String.valueOf(objects[3]));
			productDTO.setImgPath(String.valueOf(objects[4]));
			//productDTO.setImage(encodeFileToBase64Binary(new File(productDTO.getImgPath())));
			// productDTO.setImage(encodeImage(productDTO.getImgPath()));
			productDTOList.add(productDTO);
		}
		return productDTOList;
	}
	
	@Override
	public ProductDTO findProductImageListByProductId(Long modelId) {
		Object object = entityManager.createNativeQuery(getmodelImageFolder_Query).setParameter(1, modelId)
				.getSingleResult();

		String imagepath = String.valueOf(object);
		logger.info(String.valueOf(object));
		ProductDTO productDTO = new ProductDTO();
		productDTO.setImgPath(imagepath);
		return productDTO;
	}
		
	private static String encodeImage(String filePath){
			
		logger.info(" encodeImage started ");
			String imageString = null;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        
			try {
				
				BufferedImage img = ImageIO.read(new File(filePath));
				
				ImageIO.write(img,  "png" , bos);
				bos.flush();
				byte[] imageBytes = bos.toByteArray();
				
				
	            imageString = Base64.getEncoder().encodeToString(imageBytes);

	            bos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(" encodeImage ended ");
			return imageString;
		}
	
	@Override
	public String getImageByProductId(Long id) {
		
		String imagePath = entityManager.createNativeQuery(GET_IMAGE_PATH).setParameter("productId", id).getSingleResult().toString();
		
		if(imagePath != null && !imagePath.isEmpty())
		{
			return encodeFileToBase64Binary(new File(imagePath));
		}
		
		return null;
	}
			

}
