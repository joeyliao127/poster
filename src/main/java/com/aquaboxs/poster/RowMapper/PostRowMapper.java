package com.aquaboxs.poster.RowMapper;

import com.aquaboxs.poster.Model.PostModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<PostModel> {
    @Override
    public PostModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        String msg = rs.getString("msg");
        String img = rs.getString("img");
        PostModel postModel = new PostModel();
        postModel.setMsg(msg);
        postModel.setImg(img);
        return postModel;
    }
}
