package com.aquaboxs.poster.dao;

import com.aquaboxs.poster.Model.PostModel;

import java.util.List;

public interface PostDao {
    List<PostModel> getPosts();
    String uploadPost(String msg, String imgName);
}
