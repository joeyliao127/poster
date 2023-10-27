package com.aquaboxs.poster.dao;

import com.aquaboxs.poster.Model.PostModel;
import com.aquaboxs.poster.RowMapper.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDaoImpl implements PostDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<PostModel> getPosts() {
        String sql = "SELECT msg, img FROM post";
        Map<String, Object> map = new HashMap<>();
        List<PostModel> postModelList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());
        for(PostModel item : postModelList){
            System.out.println(item);
        }
        return postModelList;
    }

    @Override
    public String uploadPost(String msg, String imgName) {
        String sql = "INSERT INTO post(msg, img) Value(:msg, :imgName)";
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("imgName", imgName);
        try{
            namedParameterJdbcTemplate.update(sql, map);
            return "OK";
        }catch (RuntimeException e){
            System.out.println(e);
            return "Error";
        }
    }
}
