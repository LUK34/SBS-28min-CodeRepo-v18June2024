package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Post;

public interface PostRepo extends JpaRepository<Post, Long> 
{

}
