package com.herokuapp.cristcc2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.cristcc2.Models.Post;
import com.herokuapp.cristcc2.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository){
		this.postRepository = postRepository;
	}
	
	public Post getLatestPost(){
		return postRepository.findFirstByOrderByPostedOnDesc();
	}

	public List<Post> list() {
		return postRepository.findAllByOrderByPostedOnDesc();
	}

	public Post getBySlug(String slug) {
		return postRepository.findBySlug(slug);
	}

	public List<Post> listByAuthor(Long id) {
		return postRepository.findAllByAuthorIdOrderByPostedOnDesc(id);
	}

	public Post get(Long id) {
		Optional<Post> post = postRepository.findById(id);
		return post.get();
	}

	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	public void delete(Long id) {
		Optional<Post> post = postRepository.findById(id);
		postRepository.delete(post.get());
	}
}
