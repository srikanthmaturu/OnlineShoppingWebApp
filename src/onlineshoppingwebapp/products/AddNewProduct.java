package onlineshoppingwebapp.products;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import onlineshoppingwebapp.products.beans.ProductBean;
import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.Products;
import onlineshoppingwebapp.mysqlhelpers.ProductDBO;
import onlineshoppingwebapp.mysqlhelpers.UserDBO;
import onlineshoppingwebapp.translators.BeanDBOConvertor;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class AddNewProduct
 */
@WebServlet("/AddNewProduct")
public class AddNewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProduct() {
        super();
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ProductBean productBean = new ProductBean();
		UserDBO loggedUser = (UserDBO) request.getSession().getAttribute("loggedUserDBO");
		System.out.println(loggedUser);
		productBean.setSellerId(loggedUser.getFieldStringTypeValue("Id"));
		
		Products productsModel = new Products();
		ProductDBO product = productsModel.createNewProduct();
		productBean.setId(((Integer)product.getFieldValue("Id")).toString());
		//System.out.println((Integer)product.getFieldValue("Id"));
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		
		String productDirectory = servletContext.getInitParameter("productFilesLocation") + (Integer)product.getFieldValue("Id");
		LogPrinter.printLog(productDirectory);
		Path productDirectoryPath = Paths.get(productDirectory);
		Files.createDirectory(productDirectoryPath);
		/**
		try {
			BeanUtils.populate(productBean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Enumeration params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		**/
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		System.out.println("IsMultiPart:" + isMultipart);
		if(isMultipart){
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			String productThumbNail = "";
			ArrayList<String> productVideosLinks = new ArrayList<String>();
			ArrayList<String> productPhotosLinks = new ArrayList<String>();
			List<FileItem> items;
			try {
				items = upload.parseRequest(request);
				for(FileItem item : items) {
					System.out.println(" FieldName: " + item.getFieldName() + "Value: ");
					if ( item.isFormField () ){
						productBean.setFieldValue(item.getFieldName(), new String(item.get()));
					}
					else
					{
						System.out.println(" FileName: " + item.getFieldName());
						String filePath = item.getName();
						String fileName;
						int lastIndex = filePath.lastIndexOf("\\");
						if(lastIndex >= 0){
							fileName = filePath.substring(lastIndex);
						}{
							fileName = filePath.substring(lastIndex + 1);
						}
						File uploadedFile = new File(productDirectoryPath + "\\" + fileName);
						item.write(uploadedFile);
						switch(item.getFieldName()){
						case "productImages":
							productPhotosLinks.add(fileName);
							break;
						case "productVideos":
							productVideosLinks.add(fileName);
							break;
						case "productThumbnail":
							productThumbNail = fileName;
							break;
						default:
							break;
						}
					}
				}
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(productPhotosLinks);
				productBean.productPhotosLinks = jsonArray.toString();
				jsonArray = new JSONArray();
				jsonArray.put(productVideosLinks);
				productBean.productVideosLinks = jsonArray.toString();
				productBean.productThumbNailLink = productThumbNail;
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productBean.displayBeanFields();
		productBean.convertToDBO(productBean, ProductBean.productToProductDBOBiMap, product);
		productsModel.updateProduct(product);
		//request.getRequestDispatcher("ManageProducts").include(request, response);
		response.sendRedirect(request.getContextPath() + "/SellerPages/ManageProducts.jsp");
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
