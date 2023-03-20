package com.java.SpringBootProject.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.CommentDao;
import com.java.SpringBootProject.Entity.Comment;
import com.java.SpringBootProject.Model.CommentDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.CommentService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Transactional
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public List<CommentDTO> getAllComments() {
		List<Comment> comments = commentDao.getAllComments();
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		
		for(Comment comment: comments)
		{
			CommentDTO commentDTO = new CommentDTO();
			UserDTO userDTO = userService.getUserByID(comment.getIdUser());
			ProductDTO productDTO = productService.getProductByID(comment.getIdProduct());
			
			commentDTO.setId(comment.getId());
			commentDTO.setUserDTO(userDTO);
			commentDTO.setProductDTO(productDTO);
			commentDTO.setContent(comment.getContent());
			commentDTO.setDateComment(String.valueOf(comment.getDateComment()));
			commentDTO.setLikeNumber(comment.getLikeNumber());
			
			commentDTOs.add(commentDTO);
		}
		
		return commentDTOs;
	}

	@Override
	public void addComment(CommentDTO commentDTO) {
		Comment comment = new Comment();
		
		comment.setId(commentDTO.getId());
		comment.setIdUser(commentDTO.getUserDTO().getId());
		comment.setIdProduct(commentDTO.getProductDTO().getId());
		comment.setContent(commentDTO.getContent());
		comment.setDateComment(Date.valueOf(commentDTO.getDateComment()));
		comment.setLikeNumber(commentDTO.getLikeNumber());
		
		commentDao.addComment(comment);
	}

	@Override
	public void updateComment(int id) {
		CommentDTO commentDTO = getCommentByID(id);
		Comment comment = commentDao.getCommentByID(id);
		
		comment.setId(commentDTO.getId());
		comment.setIdUser(commentDTO.getUserDTO().getId());
		comment.setIdProduct(commentDTO.getProductDTO().getId());
		comment.setContent(commentDTO.getContent());
		comment.setDateComment(Date.valueOf(commentDTO.getDateComment()));
		comment.setLikeNumber(commentDTO.getLikeNumber());
		
		commentDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int id) {
		Comment comment = commentDao.getCommentByID(id);
		commentDao.deleteComment(comment);
		
	}

	@Override
	public CommentDTO getCommentByID(int id) {
		Comment comment = commentDao.getCommentByID(id);
		if(comment != null)
		{
			CommentDTO commentDTO = new CommentDTO();
			UserDTO userDTO = userService.getUserByID(comment.getIdUser());
			ProductDTO productDTO = productService.getProductByID(comment.getIdProduct());
			
			commentDTO.setId(comment.getId());
			commentDTO.setUserDTO(userDTO);
			commentDTO.setProductDTO(productDTO);
			commentDTO.setContent(comment.getContent());
			commentDTO.setDateComment(String.valueOf(comment.getDateComment()));
			commentDTO.setLikeNumber(comment.getLikeNumber());
			
			return commentDTO;
		}
		return null;
	}

	@Override
	public List<CommentDTO> getCommentByIdProduct(int idPro) {
		List<Comment> comments = commentDao.getCommentByIdProduct(idPro);
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		
		for(Comment comment: comments)
		{
			CommentDTO commentDTO = new CommentDTO();
			UserDTO userDTO = userService.getUserByID(comment.getIdUser());
			ProductDTO productDTO = productService.getProductByID(comment.getIdProduct());
			
			commentDTO.setId(comment.getId());
			commentDTO.setUserDTO(userDTO);
			commentDTO.setProductDTO(productDTO);
			commentDTO.setContent(comment.getContent());
			commentDTO.setDateComment(String.valueOf(comment.getDateComment()));
			commentDTO.setLikeNumber(comment.getLikeNumber());
			
			commentDTOs.add(commentDTO);
		}
		
		return commentDTOs;
	}

}
