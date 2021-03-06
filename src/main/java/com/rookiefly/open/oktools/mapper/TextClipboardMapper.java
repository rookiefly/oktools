package com.rookiefly.open.oktools.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @Classname TextClipboardMapper
 * @Description TODO
 * @Date 2021/3/26 5:32 下午
 * @Created by rookiefly
 */
@Slf4j
@Repository
public class TextClipboardMapper {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public long insert(String hash, String encodeText) {
        final String insertSql = "insert into clipboard (hash, content) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int insertRow = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, hash);
            Clob clob = con.createClob();
            clob.setString(1, encodeText);
            ps.setClob(2, clob);
            return ps;
        }, keyHolder);

        if (insertRow > 0) {
            return keyHolder.getKey().longValue();
        }
        return -1L;
    }

    public String queryTextByHash(String hash) {
        final String sql = "select content from clipboard where hash=?";
        List<String> result = jdbcTemplate.query(sql, new Object[]{hash}, (rs, rowNum) -> {
            if (rs != null) {
                return rs.getString(1);
            }
            return null;
        });
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    public String queryTextById(long id) {
        final String sql = "select content from clipboard where id=?";
        List<String> result = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            if (rs != null) {
                return rs.getString(1);
            }
            return null;
        });
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }
}
