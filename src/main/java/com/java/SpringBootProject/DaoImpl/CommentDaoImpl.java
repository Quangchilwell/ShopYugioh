package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.CommentDao;
import com.java.SpringBootProject.Entity.Comment;
import com.java.SpringBootProject.Repository.CommentRepository;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao{

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public void addComment(Comment comment) {
		commentRepository.save(comment);	
	}

	@Override
	public void updateComment(Comment comment) {
		commentRepository.save(comment);
		
	}

	@Override
	public void deleteComment(Comment comment) {
		commentRepository.delete(comment);		
	}

	@Override
	public Comment getCommentByID(int id) {
		return commentRepository.getById(id);
	}

	@Override
	public List<Comment> getCommentByIdProduct(int idPro) {
		return commentRepository.findCommentByidProduct(idPro);
	}
	
}
