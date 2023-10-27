package com.aquaboxs.poster.service;

import com.aquaboxs.poster.Model.PostModel;

import java.util.List;

public interface PostService {
    List<PostModel> getPosts();
    String uploadPost(String msg, String img);
}
