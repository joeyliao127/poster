package com.aquaboxs.poster.service;

import com.aquaboxs.poster.Model.PostModel;
import com.aquaboxs.poster.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostDao postDao;

    @Override
    public List<PostModel> getPosts() {
        return postDao.getPosts();
    }

    @Override
    public String uploadPost(String msg, String img) {
        return postDao.uploadPost(msg, img);
    }
}
