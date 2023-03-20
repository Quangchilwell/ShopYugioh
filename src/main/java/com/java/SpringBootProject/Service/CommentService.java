package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.CommentDTO;

public interface CommentService {
	public List<CommentDTO> getAllComments();

	public void addComment(CommentDTO commentDTO);

	public void updateComment(int id);

	public void deleteComment(int id);

	public CommentDTO getCommentByID(int id);
	
	public List<CommentDTO> getCommentByIdProduct(int idPro);
}
