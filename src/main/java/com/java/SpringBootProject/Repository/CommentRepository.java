package com.java.SpringBootProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	public List<Comment> findCommentByidProduct(int idPro);
}
