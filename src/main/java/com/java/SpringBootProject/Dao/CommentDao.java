package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.Comment;
import com.java.SpringBootProject.Model.CommentDTO;

public interface CommentDao {
	public List<Comment> getAllComments();
	
	public void addComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void deleteComment(Comment comment);
	
	public Comment getCommentByID(int id);
	
	public List<Comment> getCommentByIdProduct(int idPro);
}
